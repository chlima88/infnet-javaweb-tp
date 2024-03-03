package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private final Map<Integer, Product> productsDb;

    public ProductService() {
        this.productsDb = new HashMap<>();
    };

    public void add(Product product) {
        this.productsDb.put(product.getId(), product);
    };

    public Product get(int productId) {
        return this.productsDb.get(productId);
    };

    public void remove(int productId) {
        this.productsDb.remove(productId);
    };

    public Collection<Product> list() {
        return productsDb.values();
    };
}
