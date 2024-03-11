package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T> {

    Optional<T> get(int id) ;

    Collection<T> list();

    void add(T item);
    void remove(int id) ;
}
