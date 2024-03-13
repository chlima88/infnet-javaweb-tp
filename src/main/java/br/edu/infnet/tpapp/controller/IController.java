package br.edu.infnet.tpapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


public interface IController<T> {

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> add(@RequestBody T data) throws Exception;

    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<Collection<T>> list() throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<T> get(@PathVariable  int id) throws Exception;

    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> delete(@PathVariable int id) throws Exception;

}
