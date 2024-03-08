package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.services.IService;
import br.edu.infnet.tpapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value="/products")
public class ProductController extends GenericController<Product> {

    @Autowired
    ProductController(IService<Product> service) {
        super(service);
    }
}
