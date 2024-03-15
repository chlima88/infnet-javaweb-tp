package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.exceptions.CustomerNotFoundException;
import br.edu.infnet.tpapp.exceptions.CustomerServiceException;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerService implements IService<Customer> {

    private final IRepository<Customer> customerRepository;

    @Autowired
    public CustomerService(IRepository<Customer> repository) {
        this.customerRepository = repository;
    }

    @Override
    public void add(Customer customer) throws Exception {
        if (this.customerRepository.existsById(customer.getId()))
            throw new CustomerServiceException("CustomerId already in use");
        for(Customer savedCustomer : this.customerRepository.findAll()) {
            customer.compareTo(savedCustomer);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public Customer get(int customerId) throws Exception {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
    }

    @Override
    public void remove(int customerId) throws Exception {
        if (!this.customerRepository.existsById(customerId))
            throw new CustomerServiceException("CustomerId not found");
        this.customerRepository.deleteById(customerId);
    }

    @Override
    public Collection<Customer> list() {
        return this.customerRepository.findAll();
    }

    public void deactivate(int id) throws Exception {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        customer.deactivate();
        this.customerRepository.save(customer);
    }

    public void activate(int id) throws Exception {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        customer.activate();
        this.customerRepository.save(customer);
    }
}

