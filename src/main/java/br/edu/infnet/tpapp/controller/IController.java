package br.edu.infnet.tpapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


public interface IController<T> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public abstract void add(@RequestBody T data);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract Collection<T> list();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract T get(@PathVariable  int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract void delete(@PathVariable int id);

}
