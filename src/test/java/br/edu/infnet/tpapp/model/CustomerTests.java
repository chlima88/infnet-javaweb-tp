package br.edu.infnet.tpapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.infnet.tpapp.exceptions.InvalidCustomerException;
import br.edu.infnet.tpapp.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.infnet.tpapp.domain.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerTests {
	
	private Customer sut;
    DateTimeFormatter formatter;
	
	@BeforeEach
	void setUp() {

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		sut = new Customer(
				1,
				"Elberth",
				"0987654321",
				"em@ecomp.com",
				"1990-01-01"
		);
	}

	@Test
	void shouldSetAttributesInTheObject() {
		sut = new Customer();
		sut.setId(1);
		sut.setName("Luiz Maia");
		sut.setDocument("123456");
		sut.setEmail("lp@ecomp.com");
		sut.setBirthday("1980-01-01");
		sut.setCreatedAt("2012-12-20");
		
		assertEquals(1, sut.getId());
		assertEquals("Luiz Maia", sut.getName());
		assertEquals("123456", sut.getDocument());
		assertEquals("lp@ecomp.com", sut.getEmail());
		assertEquals("1980-01-01", sut.getBirthday());
		assertEquals("2012-12-20", sut.getCreatedAt());
	}

	@Test
	void shouldHaveAValidCreatedAtValue() {
		assertEquals(LocalDate.now().format(formatter), sut.getCreatedAt());
	}
	
	@Test
	void shouldReturnRankEliteForCustomerRegisteredOver12months() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().minusMonths(13).format(formatter);
		sut.setCreatedAt(date);
		
		assertEquals(Constants.CustomerRank.Level3.NAME, sut.getRank().name());
		assertEquals(Constants.CustomerRank.Level3.DISCOUNT_TX, sut.getRank().discount());
	}
	
	@Test 
	void shouldReturnRankPremiumForCustomerRegisteredBetween6and12months() {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().minusMonths(7).format(formatter);
		sut.setCreatedAt(date);
		
		assertEquals(Constants.CustomerRank.Level2.NAME, sut.getRank().name());
		assertEquals(Constants.CustomerRank.Level2.DISCOUNT_TX, sut.getRank().discount());
	}
	
	@Test 
	void shouldReturnRankBasicForCustomerRegisteredUpTo6months() {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().minusMonths(5).format(formatter);
		sut.setCreatedAt(date);
		
		assertEquals(Constants.CustomerRank.Level1.NAME, sut.getRank().name());
		assertEquals(Constants.CustomerRank.Level1.DISCOUNT_TX, sut.getRank().discount());
	}
	
	@Test
	void shouldActivateTheCustomer() {
		sut.deactivate();
		sut.activate();

		assertTrue(sut.isActive());
	}
	
	@Test
	void shouldDeactivateTheCustomer() {
		sut.activate();
		sut.deactivate();

		assertFalse(sut.isActive());
	}


	@Test
	void shouldThrowErrorWhenComparedObjectHaveSameIdValue() {
		sut.setId(1);
		Customer customer = new Customer();
		customer.setId(1);
		customer.setDocument("123");
		customer.setEmail("abc@test.com");

		Exception exception = assertThrows(
				InvalidCustomerException.class,
				()-> sut.compareTo(customer));

		assertEquals("CustomerId already in use", exception.getMessage());
	}

	@Test
	void shouldThrowErrorWhenComparedObjectHaveSameDocumentValue() {
		sut.setDocument("123");
		Customer customer = new Customer();
		customer.setId(2);
		customer.setDocument("123");
		customer.setEmail("abc@test.com");

		Exception exception = assertThrows(
				InvalidCustomerException.class,
				()-> sut.compareTo(customer));

		assertEquals("Document already in use", exception.getMessage());
	}

	@Test
	void shouldThrowErrorWhenComparedObjectHaveSameEmailValue() {
		sut.setEmail("abc@test.com");
		Customer customer = new Customer();
		customer.setId(2);
		customer.setDocument("123");
		customer.setEmail("abc@test.com");

		Exception exception = assertThrows(
				InvalidCustomerException.class,
				()-> sut.compareTo(customer));

		assertEquals("Email already in use", exception.getMessage());
	}

}
