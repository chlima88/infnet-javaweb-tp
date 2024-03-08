package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.controller.GenericController;
import br.edu.infnet.tpapp.domain.model.Product;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService extends GenericService<Product> {

    @Autowired
    public ProductService(IRepository<Product> repository) {
        super(repository);
    }
}
