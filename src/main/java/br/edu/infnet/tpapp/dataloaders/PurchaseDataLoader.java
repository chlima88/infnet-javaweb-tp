package br.edu.infnet.tpapp.dataloaders;

import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.ProductRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseDataLoader implements ApplicationRunner {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {


        PurchaseDTO purchase = new PurchaseDTO(1, 1, List.of(1,2) );
        purchaseRepository.save(purchase);
    }
}
