package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.BaseEntity;
import br.edu.infnet.tpapp.services.IService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

public abstract class GenericController<T extends BaseEntity<T>> implements IController<T> {

    protected final IService<T> service;

    GenericController(IService<T> service){
        this.service = service;
    }

    @Override
    public void add(T data) {
        try {
            this.service.add(data);
        } catch(Exception error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage(), error);
        }
    }

    @Override
    public Collection<T> list() {
        try {
            return this.service.list();
        } catch(Exception error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage(), error);
        }
    }

    @Override
    public T get(int id) {
        try {
            return this.service.get(id);
        } catch (Exception error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage(), error);
        }
    }

    @Override
    public void delete(int id) {
        try {
            this.service.remove(id);
        } catch(Exception error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage(), error);
        }
    }
}
