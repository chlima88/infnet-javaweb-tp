package br.edu.infnet.tpapp.model;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.exceptions.InvalidCustomerException;
import br.edu.infnet.tpapp.exceptions.InvalidProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseTests {

	private Product product;
	private Purchase purchase;
	private Customer customer;
	
	@BeforeEach
	void setUp() {
		product = new Product(1, "Wireless Mouse", "Microsoft", "1850", 79);
		customer = new Customer(1, "Elberth", "0987654321", "em@ecomp.com", "1990-01-01");
		customer.setCreatedAt("2012-12-20");
		purchase = new Purchase();
	}

	@Test
	void shouldBeAbleToSetAttributesInTheObject() throws InvalidProductException, InvalidCustomerException {
		purchase.setId(1);
		purchase.setCustomer(customer);
		purchase.setProducts(List.of(product));
		
		assertEquals(1, purchase.getId());
		assertEquals(customer, purchase.getCustomer());
		assertEquals(List.of(product), purchase.getProducts());
	}

	@Test
	void shouldNotBeAbleToAddDeactivatedProductsToThePurchase() throws InvalidCustomerException {
		purchase.setId(1);
		purchase.setCustomer(customer);
		product.deactivate();

		assertThrows(InvalidProductException.class, () -> purchase.setProducts(List.of(product)));
	}

	@Test
	void shouldNotBeAbleToAddDeactivatedCustomersToThePurchase() throws InvalidProductException {
		purchase.setId(1);
		purchase.setProducts(List.of(product));
		customer.deactivate();

		assertThrows(InvalidCustomerException.class, () -> purchase.setCustomer(customer));
	}

	@Test
	void shouldBeAbleToCalculateThePurchaseFinalPrice() throws InvalidProductException, InvalidCustomerException{
		purchase.setId(1);
		purchase.setCustomer(customer);
		purchase.setProducts(List.of(product,product));

		assertEquals(
				(float) purchase.getProducts().stream()
						.mapToDouble(Product::getPrice).sum() *
						( 1 - purchase.getCustomer().calculateRank().getDiscount()),
				(float) purchase.calculateFinalPrice()
		);

	}

}
