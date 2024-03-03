package br.edu.infnet.tpapp.domain.model;

public class Product {

	private int id;
	private String name;
	private String model;
	private String brand;
	private float price;
	private boolean active;

	public Product(){
		this.active = false;
	};

	public Product(int id, String name, String model, String brand, float price) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.brand = brand;
		this.price = price;
		this.active = true;
	}

	public void activate() {
		this.setActive(true);
	}

	public void deactivate() {
		this.setActive(false);
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isActive() {
		return active;
	}
	private void setActive(boolean status) {
		this.active = status;
	}
	
}
