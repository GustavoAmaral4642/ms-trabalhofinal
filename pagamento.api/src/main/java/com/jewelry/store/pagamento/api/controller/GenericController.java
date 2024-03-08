package com.jewelry.store.pagamento.api.controller;

import com.jewelry.store.pagamento.api.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class GenericController<T> {

    private final GenericService<T> service;

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T item){
        service.save(item);
        return ResponseEntity.ok().body(item);
    }

}
