package br.edu.infnet.tpapp.dataloaders;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseDataLoader implements ApplicationRunner {

    @Autowired
    GenericRepository<Purchase> purchaseRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Customer customer = new Customer(1,"Charles","123","ch@ecomp.com","2000-01-01");
        Product product = new Product(1,"Televisao","Eletro","Teste teste",1000);
        Purchase purchase = new Purchase(1,customer, List.of(product, product) );
        customerRepository.add(customer);
        productRepository.add(product);
        purchaseRepository.add(purchase);
    }
}
