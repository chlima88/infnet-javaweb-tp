package br.edu.infnet.tpapp.dataloaders;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.ProductJPARepository;
import br.edu.infnet.tpapp.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(2)
@Component
public class ProductDataLoader implements ApplicationRunner {

    @Autowired
    ProductJPARepository productRepository;
    @Autowired
    SupplierService supplierService;

    @Override
    public void run(ApplicationArguments args)  {

        Product p1 = this.supplierService.getById(16).get();
        Product p2 = this.supplierService.getById(17).get();

        this.productRepository.save(p1);
        this.productRepository.save(p2);
    }
}
