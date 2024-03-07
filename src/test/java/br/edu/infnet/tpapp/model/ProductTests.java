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
		product.setTitle("Microsoft 1850 Wireless Mouse");
		product.setCategory("Technology");
		product.setDescription("Your next Wireless Mouse");
		product.setPrice(79);
		
		assertEquals(1, product.getId());
		assertEquals("Microsoft 1850 Wireless Mouse", product.getTitle());
		assertEquals("Technology", product.getCategory());
		assertEquals("Your next Wireless Mouse", product.getDescription());
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
