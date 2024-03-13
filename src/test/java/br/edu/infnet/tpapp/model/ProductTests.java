package br.edu.infnet.tpapp.model;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.exceptions.InvalidProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTests {
	
	private Product sut;
	
	@BeforeEach
	void setUp() {
		sut = new Product(
				1,
				"Washer Dryer Samsung WD13T",
				"White goods",
				"Enjoy more effective washing with AI Ecobubble™",
				4769.10f
		);
	}

	@Test
	void shouldSetAttributesInTheObject() {
		sut.setId(2);
		sut.setTitle("Microsoft 1850 Wireless Mouse");
		sut.setCategory("Technology");
		sut.setDescription("Your next Wireless Mouse");
		sut.setPrice(79);
		
		assertEquals(2, sut.getId());
		assertEquals("Microsoft 1850 Wireless Mouse", sut.getTitle());
		assertEquals("Technology", sut.getCategory());
		assertEquals("Your next Wireless Mouse", sut.getDescription());
		assertEquals(79, sut.getPrice());
	}

	@Test
	void shouldActivateTheProduct() {
		sut.activate();

		assertTrue(sut.isActive());
	}
	
	@Test
	void shouldDeactivateTheProduct() {
		sut.activate();
		sut.deactivate();

		assertFalse(sut.isActive());
	}

	@Test
	void shouldThrowErrorWhenComparedObjectHaveSameIdValue() {
		sut.setId(1);
		Product product = new Product();
		product.setId(1);
		product.setTitle("Dummy Title");

		Exception exception = assertThrows(
				InvalidProductException.class,
				()-> sut.compareTo(product));

		assertEquals("ProductId already in use",
				exception.getMessage());
	}

	@Test
	void shouldThrowErrorWhenComparedObjectHaveSameTitleValue() {
		sut.setTitle("Dummy Title");
		Product product = new Product();
		product.setId(2);
		product.setTitle("Dummy Title");

		Exception exception = assertThrows(
				InvalidProductException.class,
				()-> sut.compareTo(product));

		assertEquals("Product Title already in use",
				exception.getMessage());
	}

}
