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
    private SupplierService supplierService;

    @Autowired
    public ProductService(IRepository<Product> repository) {
        this.productRepository = repository;
    }

    public ProductService(IRepository<Product> repository, SupplierService supplierService) {
        this.productRepository = repository;
        this.supplierService = supplierService;
    }

    @Override
    public void add(Product product) throws Exception {
        if (this.productRepository.findById(product.getId()).isPresent())
            throw new ProductServiceException("ProductId already in use");
        for (Product savedProduct: this.productRepository.findAll()){
            product.compareTo(savedProduct);
        }
        this.productRepository.save(product);
    }

    @Override
    public Product get(int productId) throws Exception {
        return this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
    }

    @Override
    public void remove(int productId) throws Exception {
        this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
        this.productRepository.deleteById(productId);
    }

    @Override
    public Collection<Product> list()  {
        return this.productRepository.findAll();
    }

    public void activate(int productId) throws Exception {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
        product.activate();
        productRepository.save(product);
    }

    public void deactivate(int productId) throws Exception {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
        product.deactivate();
        productRepository.save(product);
    }

    public Collection<Product> listSupplier()  {
        return this.supplierService.getAll();
    }

    public Product orderSupplier(int productId) throws Exception {
        Product product = this.supplierService.getById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
        product.deactivate();
        this.productRepository.save(product);
        return product;
    }
}
