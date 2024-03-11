package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
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

        if (this.purchaseRepository.get(purchaseDto.getId()).isPresent())
            throw new Exception("PurchaseId already in use");

        Customer customer = this.customerRepository.get(purchaseDto.customerId())
                .orElseThrow(() -> new Exception("Add Purchase: CustomerId not found"));

        if (!customer.isActive())
            throw new Exception("Add Purchase: Disabled Customer");

        purchaseDto.productsId().forEach(productId -> {
                Product product = this.productRepository.get(productId)
                        .orElseThrow(() -> new RuntimeException("Add Purchase: ProductId not found"));
                if (!product.isActive()) throw new RuntimeException("Add Purchase: Disabled Product");
        });

        this.purchaseRepository.add(purchaseDto);
    }

    public Purchase get(int purchaseDtoId) throws Exception {
        PurchaseDTO purchaseDTO = this.purchaseRepository.get(purchaseDtoId)
                .orElseThrow(() -> new Exception("CustomerId not found"));
        return this.loadDto(purchaseDTO);
    }

    public void remove(int purchaseDtoId) throws Exception {
        this.purchaseRepository.get(purchaseDtoId)
                .orElseThrow(() -> new Exception("CustomerId not found"));
        this.purchaseRepository.remove(purchaseDtoId);
    }

    public List<Purchase> list()  throws Exception{

        List<Purchase> list = new ArrayList<>();
        for (PurchaseDTO purchaseDTO : this.purchaseRepository.list()) {
            Purchase purchase = this.loadDto(purchaseDTO);
            list.add(purchase);
        }
        return list;
    }

    private Purchase loadDto(PurchaseDTO purchaseDto) throws Exception {

        Customer customer = this.customerRepository.get(purchaseDto.customerId())
                .orElseThrow(() -> new Exception("Purchase: CustomerId Not found"));
        List<Product> products = purchaseDto.productsId().stream().map(productId ->
        {
            try {
                return this.productRepository.get(productId)
                        .orElseThrow(() -> new Exception("Purchase: ProductId Not found"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }).toList();
        return new Purchase(purchaseDto.id(), customer, products);
    }
}
