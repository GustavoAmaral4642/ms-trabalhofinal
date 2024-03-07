package com.jewelry.store.user.api.controller;

import com.jewelry.store.user.api.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class GenericController<T> {

    private final GenericService<T> service;

    @GetMapping
    public ResponseEntity<List<T>> getAll(){
        List<T> items = service.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable Long id, String noSuchElementException){
        T item = service.get(id, noSuchElementException);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T item){
        service.save(item);
        return ResponseEntity.ok().body(item);
    }

    //TODO Ajustar parametros se der tempo
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody T item){
        service.update(item);
        return ResponseEntity.ok().build();
    }
    //TODO Ajustar parametros se der tempo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
