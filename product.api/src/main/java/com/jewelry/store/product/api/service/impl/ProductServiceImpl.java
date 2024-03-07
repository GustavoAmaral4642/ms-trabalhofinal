package com.jewelry.store.product.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.jewelry.store.product.api.domain.Product;
import com.jewelry.store.product.api.domain.User;
import com.jewelry.store.product.api.repository.ProductRepository;
import com.jewelry.store.product.api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long, ProductRepository> implements ProductService {
    private final WebClient webClient;

    public ProductServiceImpl(ProductRepository repository, WebClient webClient) {
        super(repository);
        this.webClient = webClient;
    }

    @Override
    public void save(Product item) {
        try{
            String json = this.webClient.get()
                    .uri("/user/" + String.valueOf(item.getUser_id()))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);
            if(user.getTypeuser().equals("Admin")){
                repository.save(item);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.println("O usuário não tem permissão para cadastrar produto");
            throw new IllegalArgumentException("O usuário não tem permissão para cadastrar produto");
        } catch (Exception e){
            System.out.println("Não foi encontrado usuário para registro do produto");
            throw new IllegalArgumentException();
        }
    }
}
