package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GenericRepository<T extends BaseEntity> implements IRepository<T> {
    private final Map<Integer, T> itemsDb;

    public GenericRepository(){
        this.itemsDb = new HashMap<>();
    }

    public void add(T item) {
        this.itemsDb.put(item.getId(), item);
    };

    public T get(int itemId) {
        return this.itemsDb.get(itemId);
    };

    public void remove(int itemId) {
        this.itemsDb.remove(itemId);
    };

    public Collection<T> list() {
        return this.itemsDb.values();
    };

}
