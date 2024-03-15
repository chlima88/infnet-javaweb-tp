package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/products")
public class ProductController extends GenericController<Product> implements IController<Product> {

    ProductService productService;
    @Autowired
    ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }
    @PostMapping(value = "/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable int id) throws Exception {
        this.productService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable int id) throws Exception {
        this.productService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/supplier")
    public ResponseEntity<Collection<Product>> listSupplier() {
        return ResponseEntity.ok(this.productService.listSupplier());
    }
    @PostMapping(value = "/supplier/{id}/order")
    public ResponseEntity<Product> orderToSupplier(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(this.productService.orderSupplier(id));
    }

}
