package br.edu.infnet.tpapp.repository;

import br.edu.infnet.tpapp.domain.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepository implements IRepository<Customer> {

    private final Map<Integer, Customer> itemsDb;

    public CustomerRepository() {
        this.itemsDb = new HashMap<>();
    }

    @Override
    public void save(Customer item)  {
        this.itemsDb.put(item.getId(), item);
    }

    @Override
    public Optional<Customer> getById(int itemId)  {
        return Optional.ofNullable(this.itemsDb.get(itemId));
    }

    @Override
    public void remove(int itemId) {
        this.itemsDb.remove(itemId);
    }

    @Override
    public Collection<Customer> getAll() {
        return this.itemsDb.values();
    }

    public void update(Customer customer) {
        this.itemsDb.replace(customer.getId(), customer);
    }
}
