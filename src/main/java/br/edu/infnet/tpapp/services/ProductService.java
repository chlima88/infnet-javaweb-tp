package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService implements IService<Product> {

    private final IRepository<Product> productIRepository;

    @Autowired
    public ProductService(IRepository<Product> repository) {
        this.productIRepository = repository;
    }

    @Override
    public void add(Product product) throws Exception {
        if (this.productIRepository.get(product.getId()).isPresent())
            throw new Exception("ProductId already in use");

        this.productIRepository.list().forEach(savedProduct -> {
            try {
                product.compareTo(savedProduct);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        this.productIRepository.add(product);
    }

    @Override
    public Product get(int productId) throws Exception {
        return this.productIRepository.get(productId)
                .orElseThrow(() -> new Exception("ProductId not found"));
    }

    @Override
    public void remove(int productId) throws Exception {
        this.productIRepository.get(productId)
                .orElseThrow(() -> new Exception("ProductId not found"));
        this.productIRepository.remove(productId);

    }

    @Override
    public Collection<Product> list()  {
        return this.productIRepository.list();
    }
}
