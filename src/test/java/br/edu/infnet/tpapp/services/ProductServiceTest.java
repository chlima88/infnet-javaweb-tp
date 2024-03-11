package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceTest {

    private Product product;
    private ProductService sut;


    @BeforeEach
    void setUp() {
        product = new Product(1, "Microsoft 1850 Wireless Mouse", "Technology", "Your next Wireless Mouse", 79);
        IRepository<Product> productRepository = new GenericRepository<>();
        sut = new ProductService(productRepository);
    }


    @Test
    @DisplayName("Should create a product")
    void shouldCreateAProduct() throws Exception {
        sut.add(product);

        assertTrue(sut.list().contains(product));
    }

    @Test
    @DisplayName("Should remove a product")
    void shouldRemoveAProduct() throws Exception {
        sut.add(product);
        sut.remove(product.getId());

        assertTrue(sut.list().isEmpty());
    }

    @Test
    @DisplayName("Should retrieve a product")
    void shouldRetrieveAProduct() throws Exception {
        sut.add(product);

        assertEquals(product, sut.get(product.getId()));
    }

    @Test
    @DisplayName("Should retrieve a product list")
    void shouldRetrieveAProductList() throws Exception {
        sut.add(product);
        Product product2 = new Product(2, "Washer Dryer Samsung WD13T", "White goods", "Enjoy more effective washing with AI Ecobubble™", 4769.10f);
        sut.add(product2);

        assertEquals(2,sut.list().size());
        assertEquals(product, sut.get(product.getId()));
        assertEquals(product2, sut.get(product2.getId()));
    }

    @Test
    @DisplayName("Should throw error when adding productId used previously")
    void shouldThrowErrorWhenAddingProductIdInUse() throws Exception {
        sut.add(product);
        Exception exception = assertThrows(
                Exception.class,
                () -> sut.add(product));
    }

    @Test
    @DisplayName("Should throw error when adding Product with repeated data")
    void shouldThrowErrorWhenAddingProductWithRepeatedData() throws Exception {
        sut.add(product);
        product.setId(2);
        assertThrows(
                Exception.class,
                () -> sut.add(product));
    }
}