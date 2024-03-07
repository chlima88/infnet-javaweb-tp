package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface IRepository<T> {

    T get(@PathVariable int id);

    Collection<T> list();

    void add(T product);
    void remove(int productId);
}
