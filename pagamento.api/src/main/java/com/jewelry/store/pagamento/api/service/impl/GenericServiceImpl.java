package com.jewelry.store.pagamento.api.service.impl;

import com.jewelry.store.pagamento.api.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class GenericServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements GenericService<T> {
    protected final R repository;

    public GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(T item) {
        repository.save(item);
    }

}

