package br.edu.infnet.tpapp.domain.model;

import br.edu.infnet.tpapp.exceptions.InvalidCustomerException;
import br.edu.infnet.tpapp.util.Constants;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer extends BaseEntity<Customer> {

	@Id
	@Column(name = "customerId")
	private int id;
	@Column
	private String name;
	@Column
	private String document;
	@Column
	private String email;
	@Column
	private LocalDate birthday;
	@Temporal(TemporalType.DATE)
	private LocalDate createdAt;
	@Column
	private boolean active = true;

	@OneToMany(mappedBy = "customer")
	private List<Purchase> purchases;

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

	public Customer() {
		this.setCreatedAt(LocalDate.now().format(dateFormatter));
	}
	
	public Customer(int id, String name, String document, String email, String birthday) {
		this();
		this.id = id;
		this.name = name;
		this.document = document;
		this.email = email;
		this.setBirthday(birthday);
	}

	public void activate() {
		this.setActive(true);
	}
	
	public void deactivate() {
		this.setActive(false);
	}

	public CustomerRank calculateRank() {
		if (LocalDate.now().minusMonths(12).isAfter(createdAt))
			return new CustomerRank(
					Constants.CustomerRank.Level3.NAME,
					Constants.CustomerRank.Level3.DISCOUNT_TX
			);
		if (LocalDate.now().minusMonths(6).isAfter(createdAt))
			return new CustomerRank(
					Constants.CustomerRank.Level2.NAME,
					Constants.CustomerRank.Level2.DISCOUNT_TX
			);
		return new CustomerRank(
				Constants.CustomerRank.Level1.NAME,
				Constants.CustomerRank.Level1.DISCOUNT_TX
		);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDocument() {
		return document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isActive() {
		return active;
	}
	
	private void setActive(boolean status) {
		this.active = status;
	}
	
	public String getBirthday() {
        return this.birthday.format(dateFormatter);
	}

	public CustomerRank getRank() {
		return this.calculateRank();
	}
	
	public void setBirthday(String date) {
         this.birthday = LocalDate.parse(date,dateFormatter);
	}
	
	public String getCreatedAt() {
        return this.createdAt.format(dateFormatter);
	}
	
	public void setCreatedAt(String date) {
         this.createdAt = LocalDate.parse(date,dateFormatter);
	}

	@Override
	public boolean compareTo(Customer other) throws Exception {
		if(this.getId() == other.getId())
			throw new InvalidCustomerException("CustomerId already in use");
		if(this.getDocument().equals(other.getDocument()))
			throw new InvalidCustomerException("Document already in use");
		if(this.getEmail().equals(other.getEmail()))
			throw new InvalidCustomerException("Email already in use");
		return false;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", document='" + document + '\'' +
				", email='" + email + '\'' +
				", birthday=" + birthday +
				", createdAt=" + createdAt +
				", active=" + active +
				'}';
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof Customer customer)) return false;
        return this.getId() == customer.getId()
				&& this.isActive() == customer.isActive()
				&& Objects.equals(this.getName(), customer.getName())
				&& Objects.equals(this.getDocument(), customer.getDocument())
				&& Objects.equals(this.getEmail(), customer.getEmail())
				&& Objects.equals(this.getBirthday(), customer.getBirthday())
				&& Objects.equals(this.getCreatedAt(), customer.getCreatedAt());
	}

}
