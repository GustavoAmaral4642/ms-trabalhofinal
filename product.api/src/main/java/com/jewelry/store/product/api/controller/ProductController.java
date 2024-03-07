package com.jewelry.store.product.api.controller;

import com.jewelry.store.product.api.domain.Product;
import com.jewelry.store.product.api.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController extends GenericController<Product> {

    public ProductController(GenericService<Product> service) {
        super(service);
    }
}
