package br.edu.infnet.tpapp.domain.model;

import br.edu.infnet.tpapp.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Customer {
	
	private int id;
	private String name;
	private String document;
	private String email;
	private LocalDate birthday;
	private LocalDate createdAt;
	private boolean active = true;

	public Customer() {
		this.setCreatedAt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	};
	
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.birthday.format(formatter);
	}

	public CustomerRank getRank() {
		return this.calculateRank();
	}
	
	public void setBirthday(String date) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         this.birthday = LocalDate.parse(date,formatter);
	}
	
	public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.createdAt.format(formatter);
	}
	
	public void setCreatedAt(String date) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         this.createdAt = LocalDate.parse(date,formatter);
	}

	public boolean compareTo(Customer other) throws Exception {
		if(this.getId() == other.getId())
			throw new Exception("CustomerId already in use");
		if(this.getDocument().equals(other.getDocument()))
			throw new Exception("Document already in use");
		if(this.getEmail().equals(other.getEmail()))
			throw new Exception("Email already in use");
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
