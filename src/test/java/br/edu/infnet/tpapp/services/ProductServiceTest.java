package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {
    
    private Product product;
    private ProductService productService;
    
    @BeforeEach
    void setUp() {
        product = new Product(1, "Wireless Mouse", "Microsoft", "1850", 79);
        productService = new ProductService();
    }


    @Test
    void shouldCreateAProduct() {
        productService.add(product);

        assertTrue(productService.list().contains(product));
    }

    @Test
    void shouldRemoveAProduct() {
        productService.add(product);
        productService.remove(product.getId());

        assertTrue(productService.list().isEmpty());
    }

    @Test
    void shouldRetrieveAProduct() {
        productService.add(product);

        assertEquals(product, productService.get(product.getId()));
    }

    @Test
    void shouldBeAbleToRetrieveAProductList() {
        productService.add(product);
        Product product2 = new Product(2, "Lava e Seca", "WD13T", "Samsung", 4769.10f);
        productService.add(product2);

        assertEquals(2,productService.list().size());
        assertEquals(product, productService.get(product.getId()));
        assertEquals(product2, productService.get(product2.getId()));
    }
}