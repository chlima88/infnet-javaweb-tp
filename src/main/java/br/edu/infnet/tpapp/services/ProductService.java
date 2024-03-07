package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {

    private final IRepository<Product> productRepository;

    @Autowired
    public ProductService(IRepository<Product> productRepository){
        this.productRepository = productRepository;
    }

    public void add(Product product) {
        this.productRepository.add(product);
    }

    public Product get(int productId) {
        return this.productRepository.get(productId);
    }

    public void remove(int productId) {
        this.productRepository.remove(productId);
    }

    public Collection<Product> list() {
        return this.productRepository.list();
    }
}
