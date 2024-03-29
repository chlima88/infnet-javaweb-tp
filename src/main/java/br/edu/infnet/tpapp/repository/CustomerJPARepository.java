package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerJPARepository extends CrudRepository<Customer, Integer>, IRepository<Customer> {
    Collection<Customer> findAll();
}
