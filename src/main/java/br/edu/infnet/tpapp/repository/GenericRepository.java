package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GenericRepository<T extends BaseEntity<T>> implements IRepository<T> {
    private final Map<Integer, T> itemsDb;

    public GenericRepository(){
        this.itemsDb = new HashMap<>();
    }

    public void add(T item) throws Exception {
        if (this.itemsDb.values().stream().anyMatch(savedItem -> savedItem.compareTo(item) == 0 )) {
            throw new Exception("Can't create a new entry. Invalid data");
        }
        this.itemsDb.put(item.getId(), item);
    };

    public T get(int itemId) throws Exception {
        if (this.itemsDb.containsKey(itemId))
            return this.itemsDb.get(itemId);
        else
            throw new Exception("Id not found");
    };

    public void remove(int itemId) throws Exception {
        if (!this.itemsDb.containsKey(itemId))
            throw new Exception("Id not found");
        this.itemsDb.remove(itemId);
    };

    public Collection<T> list() {
        return this.itemsDb.values();
    };

}
