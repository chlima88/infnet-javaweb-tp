package br.edu.infnet.tpapp.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
	
	private int id;
	private String name;
	private String document;
	private String email;
	private LocalDate birthday;
	private LocalDate createdAt = LocalDate.now();
	private boolean active = false;

	public Customer() {};
	
	public Customer(int id, String name, String document, String email, String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.document = document;
		this.email = email;
		this.setBirthday(birthday);
		this.setCreatedAt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

	public void activate() {
		this.setActive(true);
	}
	
	public void deactivate() {
		this.setActive(false);
	}
	
	public String getRank() {
		if (LocalDate.now().minusMonths(12).isAfter(createdAt)) return "Elite";
		if (LocalDate.now().minusMonths(6).isAfter(createdAt)) return "Premium";
		return "Basic";
	}
	
	@Override
	public String toString() {
		return String.join(
				";",
				String.valueOf(this.getId()),
				this.getName(),
				this.getDocument(),
				this.getEmail(),
				this.getBirthday(),
				this.getCreatedAt(),
				this.getRank(),
				String.valueOf(this.isActive())
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
	
}
