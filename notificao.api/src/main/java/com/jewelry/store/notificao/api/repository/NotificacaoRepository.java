package com.jewelry.store.notificao.api.repository;

import com.jewelry.store.notificao.api.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
