package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> add(@RequestBody Customer data) throws Exception {
            this.customerService.add(data);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Collection<Customer>> list() {
            return ResponseEntity.ok(this.customerService.list());
    }

    @Override
    public ResponseEntity<Customer> get(@PathVariable int id) throws Exception {
            return ResponseEntity.ok(this.customerService.get(id));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable int id) throws Exception {
            this.customerService.remove(id);
            return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/deactivate")
    public ResponseEntity<Void> disable(@PathVariable int id) throws Exception {
            this.customerService.deactivate(id);
            return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/activate")
    public ResponseEntity<Void> enable(@PathVariable int id) throws Exception {
            this.customerService.activate(id);
            return ResponseEntity.noContent().build();
    }
}
