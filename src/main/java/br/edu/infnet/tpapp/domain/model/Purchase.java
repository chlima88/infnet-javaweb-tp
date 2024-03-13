package br.edu.infnet.tpapp.domain.model;

import br.edu.infnet.tpapp.exceptions.InvalidCustomerException;
import br.edu.infnet.tpapp.exceptions.InvalidProductException;

import java.util.List;
import java.util.Objects;

public class Purchase  {

    private int id;
    private Customer customer;
    private List<Product> products;

    public Purchase() {}

    public Purchase(int id, Customer customer, List<Product> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    public float calculateFinalPrice() {
        float finalprice = 0;
        for (Product product : products) {
            finalprice = finalprice + product.getPrice();
        }
        return finalprice * (1 - this.customer.getRank().discount());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) throws InvalidCustomerException {
        if (!customer.isActive()) throw new InvalidCustomerException("Customer "+customer.getId()+" is inactive.");
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) throws InvalidProductException {
        for (Product product : products ) {
            if (!product.isActive()) throw new InvalidProductException("Product "+product.getId()+" is disabled." );
        }
        this.products = products;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Purchase purchase)) return false;
        return this.getId() == purchase.getId()
                && Objects.equals(this.getCustomer(), purchase.getCustomer())
                && Objects.equals(this.getProducts(), purchase.getProducts());
    }

}
