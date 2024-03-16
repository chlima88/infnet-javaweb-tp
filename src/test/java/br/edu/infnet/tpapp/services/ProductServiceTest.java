package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.exceptions.ProductNotFoundException;
import br.edu.infnet.tpapp.repository.GenericRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    private Product product;
    private ProductService sut;

    @BeforeEach
    void setUp() {
        product = new Product(1, "Microsoft 1850 Wireless Mouse", "Technology", "Your next Wireless Mouse", 79);
        sut = new ProductService(new GenericRepository<>(), new MockSupplierService());
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

        assertEquals(2, sut.list().size());
        assertEquals(product, sut.get(product.getId()));
        assertEquals(product2, sut.get(product2.getId()));
    }

    @Test
    @DisplayName("Should retrieve a supplier product list")
    void shouldRetrieveAVendorProductList()  {
        Collection<Product> sProducts = sut.listSupplier();
        int expect = new MockSupplierService().getAll().size();

        assertEquals(expect, sProducts.size());
    }

    @Test
    @DisplayName("Should be able to deactivate a product")
    void shouldBeAbleToDeactivateAProduct() throws Exception {
        sut.add(product);
        sut.activate(1);
        sut.deactivate(1);
        assertFalse(sut.get(1).isActive());
    }

    @Test
    @DisplayName("Should be able to activate a product")
    void shouldBeAbleToActivateAProduct() throws Exception {
        sut.add(product);
        sut.deactivate(1);
        sut.activate(1);
        assertTrue(sut.get(1).isActive());
    }

    @Test
    @DisplayName("Should request a product to the supplier")
    void shouldRequestAProductToTheSupplier() throws Exception {
        Product expect = new MockSupplierService().getById(1).get();
        Product product = sut.orderSupplier(1);
        product.activate();

        assertEquals(expect, product);
    }

    @Test
    @DisplayName("Should deactivate the requested product from Supplier")
    void shouldDeactivateTheRequestedProductFromSupplier() throws  Exception {
        Product product = sut.orderSupplier(1);
        assertFalse(product.isActive());
    }

    @Test
    @DisplayName("Should throw error requesting invalid productId to supplier")
    void shouldThrowErrorRequestingInvalidProductIdToSupplier() throws Exception {

        Exception exception = assertThrows(
                ProductNotFoundException.class,
                () -> sut.orderSupplier(3));

        assertEquals("ProductId not found",exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when adding productId used previously")
    void shouldThrowErrorWhenAddingProductIdInUse() throws Exception {
        sut.add(product);
        assertThrows(
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

    private static class MockSupplierService implements SupplierService {

        Product p1 = new Product(
                        1,
                        "Microsoft 1850 Wireless Mouse",
                        "Technology",
                        "Your next Wireless Mouse",
                        79f);

        Product p2 = new Product(
                        1,
                        "Washer Dryer Samsung WD13T",
                        "White goods",
                        "Enjoy more effective washing with AI Ecobubble™",
                        4769.10f);

        Map<Integer, Product> products;

        public MockSupplierService () {
            products = Map.of(
                    1,p1,
                    2,p2
            );
        }

        @Override
        public Optional<Product> getById(int id) {
            return Optional.ofNullable(products.get(id));
        }

        @Override
        public Collection<Product> getAll() {
            return products.values();
        }
    }
}