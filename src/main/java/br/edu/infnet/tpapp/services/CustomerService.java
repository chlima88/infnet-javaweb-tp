package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerService extends GenericService<Customer> {

    @Autowired
    public CustomerService(CustomerRepository repository) {
        super(repository);
    }
}

