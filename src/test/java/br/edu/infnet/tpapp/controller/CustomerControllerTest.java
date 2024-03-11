package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.CustomerRepository;
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
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService productRepository = new CustomerService(customerRepository);
        sut = new CustomerController(productRepository);
        customer = new Customer(1, "Elberth", "0987654321", "em@ecomp.com", "1990-01-01");
        customer.setCreatedAt("2012-12-20");
    }

    @Test
    @DisplayName("Should create an customer")
    void shouldCreateCustomer() {
        sut.add(customer);
        assertEquals(customer,sut.get(customer.getId()));
    }

    @Test
    @DisplayName("Should delete an customer")
    void shouldDeleteCustomer() {
        sut.add(customer);
        sut.delete(customer.getId());
        assertFalse(sut.list().contains(customer));
    }

    @Test
    @DisplayName("Should retrieve an customer")
    void shouldGetCustomers() {
        sut.add(customer);
        assertEquals(customer, sut.get(customer.getId()));
    }

    @Test
    @DisplayName("Should list customer")
    void shouldListCustomers() {
        sut.add(customer);
        assertTrue(sut.list().contains(customer));
    }

    @Test
    @DisplayName("Should deactivate the customer")
    void shouldDeactivateCustomers() {
        sut.add(customer);
        sut.enable(customer.getId());
        sut.disable(customer.getId());
        assertFalse(customer.isActive());
    }

    @Test
    @DisplayName("Should activate the customer")
    void shouldActivateCustomers() {
        sut.add(customer);
        sut.disable(customer.getId());
        sut.enable(customer.getId());
        assertTrue(customer.isActive());
    }

}
