package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/customers")
public class CustomerController extends GenericController<Customer> {

    CustomerService customerService;
    @Autowired
    CustomerController(CustomerService service) {
        super(service);
        this.customerService = service;
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
