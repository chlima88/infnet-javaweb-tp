package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerService implements IService<Customer> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    @Override
    public void add(Customer customer) throws Exception {
        if (this.customerRepository.get(customer.getId()).isPresent())
            throw new Exception("CustomerId already in use");
        this.customerRepository.list().forEach(savedCustomer -> {
            try {
                customer.compareTo(savedCustomer);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        this.customerRepository.add(customer);
    }

    @Override
    public Customer get(int customerId) throws Exception {
        return this.customerRepository.get(customerId)
                .orElseThrow(() -> new Exception("CustomerId not found"));
    }

    @Override
    public void remove(int customerId) throws Exception {
        this.customerRepository.get(customerId)
                .orElseThrow(() -> new Exception("CustomerId not found"));
        this.customerRepository.remove(customerId);
    }

    @Override
    public Collection<Customer> list() {
        return this.customerRepository.list();
    }

    public void deactivate(int id) throws Exception {
        Customer customer = this.customerRepository.get(id)
                .orElseThrow(() -> new Exception("CustomerId not found"));
        customer.deactivate();
        this.customerRepository.update(customer);
    }

    public void activate(int id) throws Exception {
        Customer customer = this.customerRepository.get(id)
                .orElseThrow(() -> new Exception("CustomerId not found"));
        customer.activate();
        this.customerRepository.update(customer);
    }
}

