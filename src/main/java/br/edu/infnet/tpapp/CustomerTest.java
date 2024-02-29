package br.edu.infnet.tpapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.tpapp.domain.model.Customer;

@Component
public class CustomerTest implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Customer customer1 = new Customer(1, "Charles Lima", "1234567890", "cl@ecomp.com");
		customer1.activate();
		
		Customer customer2 = new Customer();
		customer2.setId(1);
		customer2.setName("Elberth");
		customer2.setDocument("0987654321");
		customer2.setEmail("em@ecomp.com");
		customer2.activate();
		
	}


}
