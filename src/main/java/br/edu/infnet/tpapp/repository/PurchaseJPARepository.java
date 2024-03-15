package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PurchaseJPARepository extends CrudRepository<Purchase, Integer> , IRepository<Purchase>{
    Collection<Purchase> findAll();
}
