package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.exceptions.*;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final IRepository<Product> productRepository;

    @Autowired
    public PurchaseService(
            PurchaseRepository purchaseRepository,
            CustomerRepository customerRepository,
            IRepository<Product>  productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public void add(PurchaseDTO purchaseDto) throws Exception {

        if (this.purchaseRepository.getById(purchaseDto.getId()).isPresent())
            throw new PurchaseServiceException("PurchaseId already in use");

        Customer customer = this.customerRepository.getById(purchaseDto.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));

        if (!customer.isActive())
            throw new InvalidPurchaseException("Disabled Customer");

        for (int productId : purchaseDto.productsId()) {
            Product product = this.productRepository.getById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("ProductId not found"));
            if (!product.isActive()) throw new InvalidPurchaseException("Disabled Product");
        }

        this.purchaseRepository.save(purchaseDto);
    }

    public Purchase get(int purchaseDtoId) throws Exception {
        PurchaseDTO purchaseDTO = this.purchaseRepository.getById(purchaseDtoId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        return this.loadDto(purchaseDTO);
    }

    public void remove(int purchaseDtoId) throws Exception {
        this.purchaseRepository.getById(purchaseDtoId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId not found"));
        this.purchaseRepository.remove(purchaseDtoId);
    }

    public List<Purchase> list()  throws Exception{

        List<Purchase> getAll = new ArrayList<>();
        for (PurchaseDTO purchaseDTO : this.purchaseRepository.getAll()) {
            Purchase purchase = this.loadDto(purchaseDTO);
            getAll.add(purchase);
        }
        return getAll;
    }

    private Purchase loadDto(PurchaseDTO purchaseDto) throws Exception {

        Customer customer = this.customerRepository.getById(purchaseDto.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("CustomerId Not found"));
        List<Product> products = purchaseDto.productsId().stream().map(productId ->
        {
            try {
                return this.productRepository.getById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("ProductId Not found"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }).toList();
        return new Purchase(purchaseDto.id(), customer, products);
    }
}
