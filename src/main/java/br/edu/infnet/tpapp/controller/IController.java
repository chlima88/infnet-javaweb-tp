package br.edu.infnet.tpapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


public interface IController<T> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void add(@RequestBody T data);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Collection<T> list();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    T get(@PathVariable  int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void delete(@PathVariable int id);

}
