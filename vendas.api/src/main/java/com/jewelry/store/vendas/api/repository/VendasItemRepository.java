package com.jewelry.store.vendas.api.repository;

import com.jewelry.store.vendas.api.domain.VendasItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasItemRepository extends JpaRepository<VendasItem, Long> {
}
