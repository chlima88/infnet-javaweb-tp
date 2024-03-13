package br.edu.infnet.tpapp.controller;

import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.dtos.PurchaseDTO;
import br.edu.infnet.tpapp.services.PurchaseService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(value="/purchases")
public class PurchaseController  {

    PurchaseService purchaseService;

    @Autowired
    PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }


    @PostMapping
    public ResponseEntity<Void> add(@RequestBody PurchaseDTO data) throws Exception {
            this.purchaseService.add(data);
            return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Purchase>> list() throws Exception {
            return ResponseEntity.ok().body(this.purchaseService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Purchase> get(@PathVariable int id) throws Exception {
            return ResponseEntity.ok().body(this.purchaseService.get(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws Exception {
            this.purchaseService.remove(id);
            return ResponseEntity.noContent().build();
    }
}
