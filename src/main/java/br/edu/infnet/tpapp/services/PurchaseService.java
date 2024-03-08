package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService extends GenericService<Purchase> {


    CustomerRepository customerRepository;
    IRepository<Product> productRepository;
    @Autowired
    public PurchaseService(
            IRepository<Purchase> repository,
            CustomerRepository customerRepository,
            IRepository<Product>  productRepository) {
        super(repository);
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public void add(PurchaseDTO item) throws Exception {

        Customer customer = this.customerRepository.get(item.customerId());
        List<Product> products = item.productsId().stream().map(productId ->
        {
            try {
                return this.productRepository.get(productId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
        Purchase purchase = new Purchase(item.id(), customer, products);
        System.out.println(customer.getName());
        System.out.println(products.stream().map(product -> product.getTitle()).toList());
        this.repository.add(purchase);
    }
}
