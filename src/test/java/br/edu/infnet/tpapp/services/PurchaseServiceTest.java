package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.exceptions.CustomerNotFoundException;
import br.edu.infnet.tpapp.exceptions.InvalidPurchaseException;
import br.edu.infnet.tpapp.exceptions.ProductNotFoundException;
import br.edu.infnet.tpapp.exceptions.PurchaseServiceException;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PurchaseServiceTest {

    private PurchaseService sut;
    private PurchaseDTO purchaseDTO;
    private PurchaseDTO purchaseDTO2;
    private Purchase purchase;
    private Purchase purchase2;
    private Customer c1;
    private Product p1;


    @BeforeEach
    void setUp() throws Exception {
        PurchaseRepository purchaseRepository = new PurchaseRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        IRepository<Product> productRepository = new GenericRepository<>();

        sut = new PurchaseService(
                purchaseRepository,
                customerRepository,
                productRepository
        );

        c1 = new Customer(1, "Elberth Moraes", "1111", "em@ecomp.com", "1980-01-01");
        Customer c2 = new Customer(2, "Luiz Paulo Maia", "2222", "lp@ecomp.com", "1980-01-01");
        c2.setCreatedAt("2023-06-01");
        Customer c3 = new Customer(3, "Ricardo Frohlich", "3333", "rf@ecomp.com", "1980-01-01");
        c3.setCreatedAt("2023-01-01");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);

        p1 = new Product(1, "Wireless Mouse", "Microsoft", "1850", 79);
        Product p2 = new Product(2, "Monitor", "Dell", "120mhz 4k 29\" Ultra-wide OLED", 1799);

        productRepository.save(p1);
        productRepository.save(p2);

        purchase = new Purchase(1, c1, List.of(p1, p2));
        purchase2 = new Purchase(2, c2, List.of(p1, p2));

        purchaseDTO = new PurchaseDTO(1, 1, List.of(1, 2));
        purchaseDTO2 = new PurchaseDTO(2, 2, List.of(1, 2));
    }


    @Test
    @DisplayName("Should create a purchase")
    void shouldCreateAPurchase() throws Exception {
        sut.add(purchaseDTO);

        assertTrue(sut.list().contains(purchase));
    }

    @Test
    @DisplayName("Should remove a purchase")
    void shouldRemoveAPurchase() throws Exception {
        sut.add(purchaseDTO);
        sut.remove(purchaseDTO.getId());

        assertTrue(sut.list().isEmpty());
    }

    @Test
    @DisplayName("Should retrieve a purchase")
    void shouldRetrieveAPurchase() throws Exception {
        sut.add(purchaseDTO);

        assertEquals(purchase, sut.get(purchaseDTO.getId()));
    }

    @Test
    @DisplayName("Should retrieve a purchase list")
    void shouldRetrieveAPurchaseList() throws Exception {

        sut.add(purchaseDTO);
        sut.add(purchaseDTO2);

        assertEquals(2,sut.list().size());
        assertAll(
                ()-> assertEquals(purchase, sut.get(purchaseDTO.getId())),
                ()-> assertEquals(purchase2, sut.get(purchaseDTO2.getId()))
        );
    }

    @Test
    @DisplayName("Should throw error retrieving a purchase with unknown customerId")
    void shouldThrowErrorRetrievingAPurchaseWithUnknownCustomerId() {
        Exception exception = assertThrows(
                CustomerNotFoundException.class,
                () -> sut.add(new PurchaseDTO(4,100, List.of(1,2)))
        );
        assertEquals("CustomerId not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when the purchaseId is already in use")
    void shouldThrowErrorWhenPurchaseIdAlreadyUsed() throws Exception {
        sut.add(purchaseDTO);

        Exception exception = assertThrows(
                PurchaseServiceException.class,
                () -> sut.add(new PurchaseDTO(1,1,List.of(1,2))));
        assertEquals("PurchaseId already in use", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when customerId is not found")
    void shouldThrowErrorWhenCustomerIdNotFound() throws Exception {
        Exception exception = assertThrows(
                CustomerNotFoundException.class,
                () -> sut.add(new PurchaseDTO(10,10,List.of(1,2)))
        );
        assertEquals("CustomerId not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when customer is disabled")
    void shouldThrowErrorWhenCustomerIsDisabled() throws Exception {
        c1.deactivate();
        Exception exception = assertThrows(
                InvalidPurchaseException.class,
                () -> sut.add(new PurchaseDTO(10,1,List.of(1,2)))
        );
        assertEquals("Disabled Customer", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when productId is not found")
    void shouldThrowErrorWhenProductIdNotFound() throws Exception {
        Exception exception = assertThrows(
                ProductNotFoundException.class,
                () -> sut.add(new PurchaseDTO(10,1,List.of(31,32)))
        );
        assertEquals("ProductId not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when the producit id disabled")
    void shouldThrowErrorWhenProductIsDisabled() throws Exception {
        p1.deactivate();
        Exception exception = assertThrows(
                InvalidPurchaseException.class,
                () -> sut.add(new PurchaseDTO(10,1,List.of(1,2)))
        );
        assertEquals("Disabled Product", exception.getMessage());

    }
}