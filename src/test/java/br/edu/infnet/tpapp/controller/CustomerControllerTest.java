package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerControllerTest {

    CustomerController sut;
    Customer customer;


    @BeforeEach
    void setUp() {
        CustomerService customerService = new CustomerService(new GenericRepository<>());
        sut = new CustomerController(customerService);
        customer = new Customer(1, "Elberth", "0987654321", "em@ecomp.com", "1990-01-01");
        customer.setCreatedAt("2012-12-20");
    }

    @Test
    @DisplayName("Should create an customer")
    void shouldCreateCustomer() throws Exception {
        sut.add(customer);
        assertEquals(customer,sut.get(customer.getId()).getBody());
    }

    @Test
    @DisplayName("Should delete an customer")
    void shouldDeleteCustomer() throws Exception {
        sut.add(customer);
        sut.delete(customer.getId());
        assertFalse(sut.list().getBody().contains(customer));
    }

    @Test
    @DisplayName("Should retrieve an customer")
    void shouldGetCustomers() throws Exception {
        sut.add(customer);
        assertEquals(customer, sut.get(customer.getId()).getBody());
    }

    @Test
    @DisplayName("Should list customer")
    void shouldListCustomers() throws Exception {
        sut.add(customer);
        assertTrue(sut.list().getBody().contains(customer));
    }

    @Test
    @DisplayName("Should deactivate the customer")
    void shouldDeactivateCustomers() throws Exception {
        sut.add(customer);
        sut.enable(customer.getId());
        sut.disable(customer.getId());
        assertFalse(sut.get(customer.getId()).getBody().isActive());
    }

    @Test
    @DisplayName("Should activate the customer")
    void shouldActivateCustomers() throws Exception {
        sut.add(customer);
        sut.disable(customer.getId());
        sut.enable(customer.getId());
        assertTrue(sut.get(customer.getId()).getBody().isActive());
    }

}
