package com.jewelry.store.vendas.api.service;

import java.util.List;

public interface GenericService<T> {
    List<T> getAll();

    T get(Long id, String noSuchElementException);

    T save(T item);

    void update(T item);

    void delete(Long id);
}
