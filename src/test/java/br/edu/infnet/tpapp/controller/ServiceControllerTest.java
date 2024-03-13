package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceControllerTest {

    ProductController sut;
    Product product;

    @BeforeEach
    void setUp() {
        IRepository<Product> productRepository = new GenericRepository<>();
        ProductService productService = new ProductService(productRepository);
        sut = new ProductController(productService);
        product = new Product(1, "Microsoft 1850 Wireless Mouse", "Technology", "Your next Wireless Mouse", 79);
    }

    @Test
    @DisplayName("Should create an product")
    void shouldCreateProduct() throws Exception {
        sut.add(product);
        assertEquals(product,sut.get(product.getId()).getBody());
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
        sut.add(product);
        assertTrue(sut.list().getBody().contains(product));
    }

}
