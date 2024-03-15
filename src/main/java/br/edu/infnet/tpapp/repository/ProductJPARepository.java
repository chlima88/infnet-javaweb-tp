package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductJPARepository extends CrudRepository<Product, Integer>, IRepository<Product> {
    Collection<Product> findAll();
}
