package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepository extends GenericRepository<Customer> {
    public PurchaseRepository(){
        super();
    }

}
