package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import br.edu.infnet.tpapp.domain.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository(){
        super();
    }

}
