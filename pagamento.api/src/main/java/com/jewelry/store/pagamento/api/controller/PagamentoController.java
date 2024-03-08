package com.jewelry.store.pagamento.api.controller;

import com.jewelry.store.pagamento.api.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pagamento")
public class PagamentoController extends GenericController{

    public PagamentoController(GenericService service) {
        super(service);
    }
}
