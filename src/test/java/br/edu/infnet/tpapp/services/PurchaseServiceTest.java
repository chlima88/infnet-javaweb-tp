package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.exceptions.InvalidCustomerException;
import br.edu.infnet.tpapp.exceptions.InvalidProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PurchaseServiceTest {
    
    private Purchase purchase;
    private Product product;
    private Customer customer;
    private PurchaseService purchaseService;
    
    @BeforeEach
    void setUp() throws InvalidCustomerException, InvalidProductException {
        customer = new Customer(1, "Elberth", "0987654321", "em@ecomp.com", "1990-01-01");
        product = new Product(1, "Wireless Mouse", "Microsoft", "1850", 79);
        purchase = new Purchase();
        purchase.setId(1);
        purchase.setCustomer(customer);
        purchase.setProducts(List.of(product));
        purchaseService = new PurchaseService();
    }


    @Test
    void shouldCreateAPurchase() {
        purchaseService.add(purchase);

        assertTrue(purchaseService.list().contains(purchase));
    }

    @Test
    void shouldRemoveAPurchase() {
        purchaseService.add(purchase);
        purchaseService.remove(purchase.getId());

        assertTrue(purchaseService.list().isEmpty());
    }

    @Test
    void shouldRetrieveAPurchase() {
        purchaseService.add(purchase);

        assertEquals(purchase, purchaseService.get(purchase.getId()));
    }

    @Test
    void shouldBeAbleToRetrieveAPurchaseList() {
        purchaseService.add(purchase);
        Purchase purchase2 = new Purchase(2, customer, List.of(product));
        purchaseService.add(purchase2);

        assertEquals(2,purchaseService.list().size());
        assertEquals(purchase, purchaseService.get(purchase.getId()));
        assertEquals(purchase2, purchaseService.get(purchase2.getId()));
    }
}