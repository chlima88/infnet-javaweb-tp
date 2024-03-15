package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GenericRepository<T extends BaseEntity<T>> implements IRepository<T> {
    private final Map<Integer, T> itemsDb;

    public GenericRepository(){
        this.itemsDb = new HashMap<>();
    }

    public T save(T item) {
        this.itemsDb.put(item.getId(), item);
        return null;
    }

    public Optional<T> findById(int itemId)  {
        return Optional.ofNullable(this.itemsDb.get(itemId));
    }

    @Override
    public void deleteById(int itemId) {
        this.itemsDb.remove(itemId);
    }

    @Override
    public boolean existsById(int id) {
        return this.itemsDb.containsKey(id);
    }

    @Override
    public void deleteAll() {
        this.itemsDb.clear();
    }

    public Collection<T> findAll() {
        return this.itemsDb.values();
    }

}
