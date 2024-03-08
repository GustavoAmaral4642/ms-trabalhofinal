package com.jewelry.store.pagamento.api.service.helper;

import com.jewelry.store.pagamento.api.domain.Pagamento;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SearchUser {

    private final WebClient webClient;

    public SearchUser(WebClient webClient) {
        this.webClient = webClient;
    }

    public String search(Pagamento item){
        return this.webClient.get()
                .uri("/user/" + String.valueOf(item.getUser_id()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
