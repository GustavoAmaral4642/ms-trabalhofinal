package com.jewelry.store.vendas.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jewelry.store.vendas.api.domain.Vendas;
import com.jewelry.store.vendas.api.domain.VendasItem;
import com.jewelry.store.vendas.api.repository.VendasItemRepository;
import com.jewelry.store.vendas.api.repository.VendasRepository;
import com.jewelry.store.vendas.api.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.amqp.core.AmqpTemplate;

@Service
public class VendasServiceImpl extends GenericServiceImpl<Vendas, Long, VendasRepository> implements VendasService {

    @Autowired
    private VendasItemRepository vendasItemRepository;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String rountingkey;

    private final AmqpTemplate rabbitTemplate;

    private final WebClient webClient;

    public VendasServiceImpl(VendasRepository repository, WebClient webClient, AmqpTemplate rabbitTemplate) {
        super(repository);
        this.webClient = webClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void save(Vendas vendas) {
        try {

            // TODO Enviar notificação da venda
            // TODO registrar pagamento

            this.webClient.get()
                    .uri("/user/" +  String.valueOf(vendas.getUser_id()))
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(response -> {
                        if(response.statusCode().equals(HttpStatus.OK)){
                            Vendas vnd = repository.save(vendas);

                            for(VendasItem item: vnd.getVendasItems()){
                                VendasItem vendasItem = new VendasItem();

                                vendasItem.setVendas(vnd);
                                vendasItem.setProduct_id(item.getProduct_id());

                                vendasItemRepository.save(vendasItem);
                            }

                            this.sendNotification(vendas);
                            return response.toEntity(String.class);
                        }
                        else if(response.statusCode().equals(HttpStatus.NOT_FOUND)){
                            throw new IllegalArgumentException();
                        } else {
                            return response.createError();
                        }
                    })
                    .block();

        }
        catch (Exception e) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

    public void sendNotification(Vendas venda) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            String json = mapper.writeValueAsString(venda);

            rabbitTemplate.convertAndSend(exchange, rountingkey, json);
        } catch (JsonProcessingException e){
            return ;
        }

    }
}
