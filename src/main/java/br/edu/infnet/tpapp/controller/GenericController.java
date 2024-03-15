package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import br.edu.infnet.tpapp.services.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public abstract class GenericController<T extends BaseEntity<T>> implements IController<T> {

    protected final IService<T> service;

    GenericController(IService<T> service){
        this.service = service;
    }

    @Override
    public ResponseEntity<T> get(int id) throws Exception {
        return ResponseEntity.ok(this.service.get(id));
    }

    @Override
    public ResponseEntity<Void> delete(int id) throws Exception {
        this.service.remove(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Collection<T>> list() {
        return ResponseEntity.ok(service.list());
    }

    @Override
    public ResponseEntity<Void> add(T item) throws Exception {
        this.service.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
