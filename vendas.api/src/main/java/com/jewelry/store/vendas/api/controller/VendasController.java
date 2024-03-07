package com.jewelry.store.vendas.api.controller;

import com.jewelry.store.vendas.api.domain.Vendas;
import com.jewelry.store.vendas.api.service.VendasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vendas")
public class VendasController extends GenericController<Vendas>{
    public VendasController(VendasService service){ super(service); }
}
