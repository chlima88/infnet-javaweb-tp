package br.edu.infnet.tpapp.dataloaders;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.repository.CustomerJPARepository;
import br.edu.infnet.tpapp.repository.ProductJPARepository;
import br.edu.infnet.tpapp.repository.PurchaseJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(3)
@Component
public class PurchaseDataLoader implements ApplicationRunner {

    @Autowired
    PurchaseJPARepository purchaseRepository;

    @Autowired
    CustomerJPARepository customerRepository;

    @Autowired
    ProductJPARepository productRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Customer c1 = this.customerRepository.findById(97).get();
        Product p1 = this.productRepository.findById(16).get();
        Product p2 = this.productRepository.findById(17).get();

        Purchase purchase = new Purchase(1, c1, List.of(p1,p2));
        purchaseRepository.save(purchase);
    }
}
