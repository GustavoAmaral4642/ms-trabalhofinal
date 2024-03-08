package com.jewelry.store.vendas.api.service.impl.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jewelry.store.vendas.api.domain.Vendas;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendNotification {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String rountingkey;

    public SendNotification(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final AmqpTemplate rabbitTemplate;

    public void send(Vendas venda) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            String json = mapper.writeValueAsString(venda);

            rabbitTemplate.convertAndSend(exchange, rountingkey, json);
        } catch (JsonProcessingException e) {
            return;
        }
    }
}
