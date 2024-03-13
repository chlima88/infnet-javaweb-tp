package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import br.edu.infnet.tpapp.services.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PurchaseControllerTest {

    private PurchaseController sut;

    private PurchaseDTO purchaseDTO;
    private PurchaseDTO purchaseDTO2;
    private Purchase purchase;
    private Purchase purchase2;
    private Product p1;
    private Customer c1;

    @BeforeEach
    void setUp() {

        PurchaseRepository purchaseRepository = new PurchaseRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        IRepository<Product> productRepository = new GenericRepository<>();
        PurchaseService purchaseService = new PurchaseService(
                purchaseRepository,
                customerRepository,
                productRepository
        );
        sut = new PurchaseController(purchaseService);

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
    @DisplayName("Should create an purchase")
    void shouldCreatePurchase() throws Exception {
        sut.add(purchaseDTO);

        assertEquals(purchase, sut.get(purchaseDTO.getId()).getBody());
    }

    @Test
    @DisplayName("Should delete an purchase")
    void shouldDeletePurchase() throws Exception {
        sut.add(purchaseDTO);
        sut.delete(purchaseDTO.getId());

        assertFalse(sut.list().getBody().contains(purchase));
    }

    @Test
    @DisplayName("Should retrieve an purchase")
    void shouldGetPurchases() throws Exception {
        sut.add(purchaseDTO);
        assertEquals(purchase, sut.get(purchaseDTO.getId()).getBody());
    }

    @Test
    @DisplayName("Should list purchase")
    void shouldListPurchases() throws Exception {
        sut.add(purchaseDTO);
        assertTrue(sut.list().getBody().contains(purchase));
    }

}
