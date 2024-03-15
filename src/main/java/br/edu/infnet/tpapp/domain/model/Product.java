package br.edu.infnet.tpapp.domain.model;

import br.edu.infnet.tpapp.exceptions.InvalidProductException;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Product")
public class Product extends  BaseEntity<Product> {

	@Id
	@Column(name = "productId")
	private int id;
	@Column
	private String title;
	@Column
	private String category;

	@Column(columnDefinition = "text")
	private String description;
	@Column
	private float price;
	@Column
	private boolean active;
	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<Purchase> purchases;

	public Product(){
		this.active = true;
	}

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

	@Override
	public boolean compareTo(Product other) throws InvalidProductException {
		if(other.getId() == this.getId())
			throw new InvalidProductException("ProductId already in use");
		if(other.getTitle().equalsIgnoreCase(this.getTitle()))
			throw new InvalidProductException("Product Title already in use");
		return false;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", category='" + category + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", active=" + active +
				"} ";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || this.getClass() != other.getClass()) return false;
		Product product = (Product) other;
		return this.getId() == product.getId()
				&& Float.compare(this.getPrice(), product.getPrice()) == 0
				&& this.isActive() == product.isActive()
				&& Objects.equals(this.getTitle(), product.getTitle())
				&& Objects.equals(this.getCategory(), product.getCategory())
				&& Objects.equals(this.getDescription(), product.getDescription());
	}
}