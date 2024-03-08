package com.jewelry.store.pagamento.api.repository;

import com.jewelry.store.pagamento.api.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
