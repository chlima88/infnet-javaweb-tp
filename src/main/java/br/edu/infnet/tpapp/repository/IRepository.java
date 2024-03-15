package br.edu.infnet.tpapp.repository;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T> {

    Optional<T> findById(int id) ;

    Collection<T> findAll();

    T save(T item);

    void deleteById(int purchaseDtoId);

    boolean existsById(int id);

    void deleteAll();
}
