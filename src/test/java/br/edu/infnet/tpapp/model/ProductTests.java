package br.edu.infnet.tpapp.model;

import br.edu.infnet.tpapp.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTests {
	
	private Product product;
	
	@BeforeEach
	void setUp() {
		product = new Product(); 
	}

	@Test
	void shouldBeAbleToSetAttributesInTheObject() {		
		product.setId(1);
		product.setName("Wireless Mouse");
		product.setBrand("Microsoft");
		product.setModel("1850");
		product.setPrice(79);
		
		assertEquals(1, product.getId());
		assertEquals("Wireless Mouse", product.getName());
		assertEquals("Microsoft", product.getBrand());
		assertEquals("1850", product.getModel());
		assertEquals(79, product.getPrice());
	}

	@Test
	void shouldBeAbleToActivateTheProduct() {
		product.activate();

		assertTrue(product.isActive());
	}
	
	@Test
	void shouldBeAbleToDeactivateTheProduct() {
		product.activate();
		product.deactivate();

		assertFalse(product.isActive());
	}

}
