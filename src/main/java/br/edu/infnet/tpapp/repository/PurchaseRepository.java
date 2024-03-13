package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PurchaseRepository implements IRepository<PurchaseDTO> {

    private final Map<Integer, PurchaseDTO> itemsDb;

    public PurchaseRepository(){
        this.itemsDb = new HashMap<>();
    }

    @Override
    public void save(PurchaseDTO item) {
        this.itemsDb.put(item.getId(), item);
    }

    @Override
    public Optional<PurchaseDTO> getById(int itemId) {
            return Optional.ofNullable(this.itemsDb.get(itemId));
    }

    @Override
    public void remove(int itemId) {
        this.itemsDb.remove(itemId);
    }

    @Override
    public Collection<PurchaseDTO> getAll() {
        return this.itemsDb.values();
    }

}
