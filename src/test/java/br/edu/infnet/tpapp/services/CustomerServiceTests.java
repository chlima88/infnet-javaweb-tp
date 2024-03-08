package br.edu.infnet.tpapp.services;

import java.util.*;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTests {

    CustomerService customerService;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Elberth", "0987654321", "em@ecomp.com", "1990-01-01");
        customer.setCreatedAt("2012-12-20");
        CustomerRepository customerRepository = new CustomerRepository();
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void shouldCreateACustomer() throws Exception {
        customerService.add(customer);

        assertTrue(customerService.list().contains(customer));
    }

    @Test
    void shouldRemoveACustomer() throws Exception {
        customerService.add(customer);
        customerService.remove(customer.getId());

        assertTrue(customerService.list().isEmpty());
    }

    @Test
    void shouldRetrieveACustomer() throws Exception {
        customerService.add(customer);

        assertEquals(customer, customerService.get(customer.getId()));
    }

    @Test
    void shouldBeAbleToRetrieveACustomerList() throws Exception {
        customerService.add(customer);
        Customer customer2 = new Customer(2, "Charles", "123", "cl@ecomp.com", "2000-11-11");
        customerService.add(customer2);

        assertEquals(2,customerService.list().size());
        assertEquals(customer, customerService.get(customer.getId()));
        assertEquals(customer2, customerService.get(customer2.getId()));
    }
}
