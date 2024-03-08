package br.edu.infnet.tpapp.services;

import java.util.Collection;

public interface IService<T> {

    public void add(T item) throws Exception;

    public T get(int itemId) throws Exception;

    public void remove(int itemId) throws Exception;

    public Collection<T> list();
}

