package com.jewelry.store.vendas.api.repository;

import com.jewelry.store.vendas.api.domain.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
}
