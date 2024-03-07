package br.edu.infnet.tpapp.domain.model;

public class Product extends BaseEntity {

	private int id;
	private String title;
	private String category;
	private String description;
	private float price;
	private boolean active;

	public Product(){
		this.active = false;
	};

	public Product(int id, String title, String category, String description, float price) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = description;
		this.price = price;
		this.active = true;
	}

	public void activate() {
		this.setActive(true);
	}

	public void deactivate() {
		this.setActive(false);
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setActive(boolean active) {
		this.active = active;
	}
}
