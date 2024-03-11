package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(value="/customers")
public class CustomerController implements IController<Customer> {

    CustomerService customerService;
    @Autowired
    CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @Override
    public void add(@RequestBody Customer data) {
        try {
            this.customerService.add(data);
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error.getMessage(),
                    error);
        }
    }

    @Override
    public Collection<Customer> list() {
        try {
            return this.customerService.list();
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    error.getMessage(),
                    error);
        }
    }

    @Override
    public Customer get(@PathVariable int id) {
        try {
            return this.customerService.get(id);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    error.getMessage(),
                    error);
        }
    }

    @Override
    public void delete(@PathVariable int id) {
        try {
            this.customerService.remove(id);
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error.getMessage(),
                    error);
        }
    }

    @PostMapping(value = "/{id}/deactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void disable(@PathVariable int id) {
        try {
            this.customerService.deactivate(id);
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error.getMessage(),
                    error);
        }
    }

    @PostMapping(value = "/{id}/activate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void enable(@PathVariable int id) {
        try {
            this.customerService.activate(id);
        } catch(Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error.getMessage(),
                    error);
        }
    }
}
