package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Product get(int id) {
        try {
            return this.productService.get(id);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    error.getMessage(),
                    error);
        }
    }

    @Override
    public void delete(int id) {
        try {
            this.productService.remove(id);
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error.getMessage(),
                    error);
        }
    }

    @Override
    public Collection<Product> list() {
        try {
            return this.productService.list();
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    error.getMessage(),
                    error);
        }
    }

    @Override
    public void add(Product item) {
        try {
            this.productService.add(item);
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error.getMessage(),
                    error);
        }
    }
}
