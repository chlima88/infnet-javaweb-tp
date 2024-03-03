package br.edu.infnet.tpapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.infnet.tpapp.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.infnet.tpapp.domain.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerTests {
	
	private Customer customer;
    DateTimeFormatter formatter;
	
	@BeforeEach
	void setUp() {

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		customer = new Customer(); 
	}

	@Test
	void shouldBeAbleToSetAttributesInTheObject() {		
		customer.setId(1);
		customer.setName("Elberth");
		customer.setDocument("0987654321");
		customer.setEmail("em@ecomp.com");
		customer.setBirthday("1990-01-01");
		customer.setCreatedAt("2012-12-20");
		
		assertEquals(1, customer.getId());
		assertEquals("Elberth", customer.getName());
		assertEquals("0987654321", customer.getDocument());
		assertEquals("em@ecomp.com", customer.getEmail());
		assertEquals("1990-01-01", customer.getBirthday());
		assertEquals("2012-12-20", customer.getCreatedAt());
	}

	@Test
	void shouldHaveAValidCreatedAtValue() {
		assertEquals(LocalDate.now().format(formatter), customer.getCreatedAt());
	}
	
	@Test
	void shouldReturnRankEliteForCustomerRegisteredOver12months() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().minusMonths(13).format(formatter);
		customer.setCreatedAt(date);
		
		assertEquals(Constants.CustomerRank.Level3.NAME, customer.getRank().getName());
		assertEquals(Constants.CustomerRank.Level3.DISCOUNT_TX, customer.getRank().getDiscount());
	}
	
	@Test 
	void shouldReturnRankPremiumForCustomerRegisteredBetween6and12months() {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().minusMonths(7).format(formatter);
		customer.setCreatedAt(date);
		
		assertEquals(Constants.CustomerRank.Level2.NAME, customer.getRank().getName());
		assertEquals(Constants.CustomerRank.Level2.DISCOUNT_TX, customer.getRank().getDiscount());
	}
	
	@Test 
	void shouldReturnRankBasicForCustomerRegisteredUpTo6months() {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().minusMonths(5).format(formatter);
		customer.setCreatedAt(date);
		
		assertEquals(Constants.CustomerRank.Level1.NAME, customer.getRank().getName());
		assertEquals(Constants.CustomerRank.Level1.DISCOUNT_TX, customer.getRank().getDiscount());
	}
	
	@Test
	void shouldBeAbleToActivateTheCustomer() {
		customer.deactivate();
		customer.activate();

		assertTrue(customer.isActive());
	}
	
	@Test
	void shouldBeAbleToDeactivateTheCustomer() {
		customer.activate();
		customer.deactivate();

		assertFalse(customer.isActive());
	}

}
