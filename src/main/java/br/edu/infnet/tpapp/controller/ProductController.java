package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    ProductService productService;

    @Autowired
    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable int id) {
        return this.productService.get(id);
    }

    @GetMapping(value = "/")
    public Collection<Product> listProducts() {
        return this.productService.list();
    }
}
