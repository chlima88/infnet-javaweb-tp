package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(value="/products")
public class ProductController implements IController<Product> {


    ProductService productService;
    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<Product> get(int id) throws Exception {
        return ResponseEntity.ok(this.productService.get(id));
    }

    @Override
    public ResponseEntity<Void> delete(int id) throws Exception {
        this.productService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Collection<Product>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @Override
    public ResponseEntity<Void> add(Product item) throws Exception {
        this.productService.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
