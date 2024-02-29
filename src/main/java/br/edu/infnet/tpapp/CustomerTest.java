package br.edu.infnet.tpapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.tpapp.domain.model.Customer;

@Component
public class CustomerTest implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// Creates a Customer instance
		Customer customer = new Customer(1, "Charles Lima", "1234567890", "cl@ecomp.com", "1990-01-01");
		System.out.println(customer);
		// Activate the customer account
		customer.activate();
		System.out.println(customer);
		// Deactivate the customer account
		customer.deactivate();
		System.out.println(customer);
		
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// Set the creation date to 2 months earlier
		customer.setCreatedAt(LocalDate.now().minusMonths(2).format(dateformatter));
		System.out.println(customer.getRank());
		// Set the creation date to 7 months before
		customer.setCreatedAt(LocalDate.now().minusMonths(7).format(dateformatter));
		System.out.println(customer.getRank());
		// Set the creation date to 13 months before
		customer.setCreatedAt(LocalDate.now().minusMonths(13).format(dateformatter));
		System.out.println(customer.getRank());
		
	}


}
