package br.edu.infnet.tpapp.dataloaders;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.CustomerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(1)
@Component
public class CustomerDataLoader implements ApplicationRunner {

    @Autowired
    CustomerJPARepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Customer c1 = new Customer(97,"Elberth Moraes","1111","em@ecomp.com","1980-01-01");
        Customer c2 = new Customer(98,"Luiz Paulo Maia","2222","lp@ecomp.com","1980-01-01");
        c2.setCreatedAt("2023-06-01");
        Customer c3 = new Customer(99,"Ricardo Frohlich","3333","rf@ecomp.com","1980-01-01");
        c3.setCreatedAt("2023-01-01");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);

    }
}
