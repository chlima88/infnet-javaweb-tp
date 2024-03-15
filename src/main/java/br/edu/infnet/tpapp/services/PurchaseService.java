package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.exceptions.*;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService  {

    private final IRepository<Purchase> purchaseRepository;
    private final IService<Customer> customerService;
    private final IService<Product> productService;

    @Autowired
    public PurchaseService(
            IRepository<Purchase> purchaseRepository,
            IService<Customer> customerService,
            IService<Product>  productService) {
        this.purchaseRepository = purchaseRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public void add(PurchaseDTO purchaseDto) throws Exception {

        if (this.purchaseRepository.findById(purchaseDto.getId()).isPresent())
            throw new PurchaseServiceException("PurchaseId already in use");

        Customer customer = this.customerService.get(purchaseDto.customerId());

        if (!customer.isActive())
            throw new InvalidPurchaseException("Disabled Customer");

        List<Product> products = new ArrayList<>();
        for (int productId : purchaseDto.productsId()) {
            Product product = this.productService.get(productId);
            if (!product.isActive()) throw new InvalidPurchaseException("Disabled Product");
            products.add(product);
        }

        this.purchaseRepository.save(new Purchase(purchaseDto.getId(), customer, products));
    }

    public Purchase get(int purchaseDtoId) throws Exception {
        return this.purchaseRepository.findById(purchaseDtoId)
                .orElseThrow(() -> new PurchaseServiceException("PurchaseId not found"));
    }

    public void remove(int purchaseDtoId) throws Exception {
        this.purchaseRepository.findById(purchaseDtoId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        this.purchaseRepository.deleteById(purchaseDtoId);
    }

    public List<Purchase> list()  throws Exception{

        List<Purchase> purchases = new ArrayList<>();
        for (Purchase purchase : this.purchaseRepository.findAll()) {
            purchases.add(purchase);
        }
        return purchases;
    }

    private Purchase loadDto(PurchaseDTO purchaseDto) throws Exception {

        Customer customer = this.customerService.get(purchaseDto.customerId());

        List<Product> products = new ArrayList<>();
        for (int productId : purchaseDto.productsId() ){
            products.add(this.productService.get(productId));
        }

        return new Purchase(purchaseDto.id(), customer, products);
    }
}
