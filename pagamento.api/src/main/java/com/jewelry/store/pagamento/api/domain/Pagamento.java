package com.jewelry.store.pagamento.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "`pagamento`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long user_id;

    @Column(nullable = false, updatable = false)
    private String parcela;

    @Column(nullable = false, updatable = false)
    private String dataVencimento;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
}
