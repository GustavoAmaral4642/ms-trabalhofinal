package com.jewelry.store.vendas.api.service.impl;

import com.jewelry.store.vendas.api.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class GenericServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements GenericService<T> {
    protected final R repository;

    public GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T get(Long id, String noSuchElementException) {
        return repository.findById((ID) id).orElseThrow(() -> new NoSuchElementException(noSuchElementException + id));
    }
    @Override
    public T save(T item) {
        return repository.save(item);
    }

    @Override
    public void update(T item) {
        repository.save(item);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById((ID) id);
    }
}

