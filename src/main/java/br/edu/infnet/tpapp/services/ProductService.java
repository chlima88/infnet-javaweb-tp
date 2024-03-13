package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.exceptions.ProductNotFoundException;
import br.edu.infnet.tpapp.exceptions.ProductServiceException;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService implements IService<Product> {

    private final IRepository<Product> productRepository;

    @Autowired
    public ProductService(IRepository<Product> repository) {
        this.productRepository = repository;
    }

    @Override
    public void add(Product product) throws Exception {
        if (this.productRepository.getById(product.getId()).isPresent())
            throw new ProductServiceException("ProductId already in use");
        for (Product savedProduct: this.productRepository.getAll()){
            product.compareTo(savedProduct);
        }
        this.productRepository.save(product);
    }

    @Override
    public Product get(int productId) throws Exception {
        return this.productRepository.getById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
    }

    @Override
    public void remove(int productId) throws Exception {
        this.productRepository.getById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
        this.productRepository.remove(productId);

    }

    @Override
    public Collection<Product> list()  {
        return this.productRepository.getAll();
    }
}
