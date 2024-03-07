package br.edu.infnet.tpapp.domain.model;

import br.edu.infnet.tpapp.exceptions.InvalidCustomerException;
import br.edu.infnet.tpapp.exceptions.InvalidProductException;
import br.edu.infnet.tpapp.util.Constants;

import java.util.List;
import java.util.Objects;

public class Purchase extends BaseEntity {

    private int id;
    private Customer customer;
    private List<Product> products;

    public Purchase() {};

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

        return finalprice * (1 - this.customer.getRank().getDiscount());

    }

    @Override
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
        if (!customer.isActive()) throw new InvalidCustomerException();
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) throws InvalidProductException {
        for (Product product : products ) {
            if (!product.isActive()) throw new InvalidProductException();
        }
        this.products = products;
    }


}
