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

    public void save(T item) {
        this.itemsDb.put(item.getId(), item);
    }

    public Optional<T> getById(int itemId)  {
        return Optional.ofNullable(this.itemsDb.get(itemId));
    }

    public void remove(int itemId) {
        this.itemsDb.remove(itemId);
    }

    public Collection<T> getAll() {
        return this.itemsDb.values();
    }

}
