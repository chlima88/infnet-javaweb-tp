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
@RequestMapping(value="/customers", produces = "application/json")
public class CustomerController {

    CustomerService service;
    @Autowired
    CustomerController(CustomerService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void add(@RequestBody Customer data) {
        try {
            this.service.add(data);
        } catch(Exception error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage(), error);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Customer> list() {
        try {
            return this.service.list();
        } catch(Exception error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage(), error);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Customer get(@PathVariable int id) {
        try {
            return this.service.get(id);
        } catch (Exception error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage(), error);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable int id) {
        try {
            this.service.remove(id);
        } catch(Exception error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage(), error);
        }

    }
}
