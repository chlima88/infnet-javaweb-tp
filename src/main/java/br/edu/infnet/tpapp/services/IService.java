package br.edu.infnet.tpapp.services;

import java.util.Collection;

public interface IService<T> {

    void add(T item) throws Exception;

    T get(int itemId) throws Exception;

    void remove(int itemId) throws Exception;

    Collection<T> list();
}

