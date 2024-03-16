package br.edu.infnet.tpapp.services;


import br.edu.infnet.tpapp.domain.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;


@Service
@FeignClient(url = "https://fakestoreapi.com/products", name = "FakeStoreAPI")
public interface SupplierService {

    @GetMapping(value = "/{id}")
    Optional<Product> getById(@PathVariable int id);

    @GetMapping
    Collection<Product> getAll();

}
