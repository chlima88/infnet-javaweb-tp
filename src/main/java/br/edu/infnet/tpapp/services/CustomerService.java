package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;

import java.util.*;

public class CustomerService {

    private final Map<Integer, Customer> customersDb;

    public CustomerService() {
        this.customersDb = new HashMap<>();
    };

    public void add(Customer customer) {
        this.customersDb.put(customer.getId(), customer);
    };

    public Customer get(int customerId) {
        return this.customersDb.get(customerId);
    };

    public void remove(int customerId) {
        this.customersDb.remove(customerId);
    };

    public Collection<Customer> list() {
        return customersDb.values();
    };
}
