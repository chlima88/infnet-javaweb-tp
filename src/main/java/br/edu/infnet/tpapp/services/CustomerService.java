package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.exceptions.CustomerNotFoundException;
import br.edu.infnet.tpapp.exceptions.CustomerServiceException;
import br.edu.infnet.tpapp.exceptions.InvalidProductException;
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
        if (this.customerRepository.getById(customer.getId()).isPresent())
            throw new CustomerServiceException("CustomerId already in use");
        for(Customer savedCustomer : this.customerRepository.getAll()) {
            customer.compareTo(savedCustomer);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public Customer get(int customerId) throws Exception {
        return this.customerRepository.getById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
    }

    @Override
    public void remove(int customerId) throws Exception {
        this.customerRepository.getById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        this.customerRepository.remove(customerId);
    }

    @Override
    public Collection<Customer> list() {
        return this.customerRepository.getAll();
    }

    public void deactivate(int id) throws Exception {
        Customer customer = this.customerRepository.getById(id)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        customer.deactivate();
        this.customerRepository.update(customer);
    }

    public void activate(int id) throws Exception {
        Customer customer = this.customerRepository.getById(id)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        customer.activate();
        this.customerRepository.update(customer);
    }
}

