package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import br.edu.infnet.tpapp.repository.IRepository;

import java.util.Collection;


public class GenericService<T extends BaseEntity<T>> implements IService<T> {

    protected final IRepository<T> repository;

    public GenericService(IRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public void add(T item) throws Exception {
        if (this.repository.getById(item.getId()).isPresent())
            throw new Exception("ProductId not found");
        this.repository.save(item);
    }

    public T get(int itemId) throws Exception {
        return this.repository.getById(itemId)
                .orElseThrow(() -> new Exception("Item Id not found"));
    }

    public void remove(int itemId) throws Exception {
        this.repository.getById(itemId)
                .orElseThrow(() -> new Exception("Item Id not found"));
        this.repository.remove(itemId);
    }

    public Collection<T> list() {
        return this.repository.getAll();
    }

}

