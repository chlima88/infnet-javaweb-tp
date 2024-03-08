package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


public abstract class GenericService<T extends BaseEntity<T>> implements IService<T> {

    protected final IRepository<T> repository;

    public GenericService(IRepository<T> repository){
        this.repository = repository;
    }

    @Override
    public void add(T item) throws Exception {
        this.repository.add(item);
    }

    public T get(int itemId) throws Exception {
        return this.repository.get(itemId);
    }

    public void remove(int itemId) throws Exception {
        this.repository.remove(itemId);
    }

    public Collection<T> list() {
        return this.repository.list();
    }

}

