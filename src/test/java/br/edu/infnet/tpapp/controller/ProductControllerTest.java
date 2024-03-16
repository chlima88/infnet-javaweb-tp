package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;


@SpringBootTest
public class ProductControllerTest {

    ProductController sut;
    Product product;


    @BeforeEach
    void setUp() {
        product = new Product(
                1,
                "Microsoft 1850 Wireless Mouse",
                "Technology",
                "Your next Wireless Mouse",
                79);
        ProductService productService = new ProductService(new GenericRepository<>());
        sut = new ProductController(productService);
    }

    @Test
    @DisplayName("Should create an product")
    void shouldCreateProduct() throws Exception {
        sut.add(product);
        assertEquals(product, sut.get(product.getId()).getBody());
    }

    @Test
    @DisplayName("Should delete an product")
    void shouldDeleteProduct() throws Exception {
        sut.add(product);
        sut.delete(product.getId());
        assertFalse(sut.list().getBody().contains(product));
    }

    @Test
    @DisplayName("Should retrieve an product")
    void shouldGetProducts() throws Exception {
        sut.add(product);
        assertEquals(product, sut.get(product.getId()).getBody());
    }

    @Test
    @DisplayName("Should list product")
    void shouldListProducts() throws Exception {
        List<Product> expect = List.of(product);
        sut.add(product);

        assertTrue(expect.containsAll(sut.list().getBody()));
    }

    @Test
    @DisplayName("Should deactivate the product")
    void shouldDeactivateProducts() throws Exception {
        sut.add(product);
        sut.activate(product.getId());
        sut.deactivate(product.getId());
        assertFalse(sut.get(product.getId()).getBody().isActive());
    }

    @Test
    @DisplayName("Should activate the product")
    void shouldActivateProducts() throws Exception {
        sut.add(product);
        sut.deactivate(product.getId());
        sut.activate(product.getId());
        assertTrue(sut.get(product.getId()).getBody().isActive());
    }

}
