package com.jewelry.store.notificao.api.component.impl;

import com.jewelry.store.notificao.api.component.RabbitMQComponent;
import com.jewelry.store.notificao.api.service.impl.EmailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class RabbitMQComponentImpl implements RabbitMQComponent {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Autowired
    private EmailServiceImpl emailService;

    private final WebClient webClient;

    public RabbitMQComponentImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @RabbitListener(queues = "order_notification")
    public void handleMessage(String message) {

        Map<String, Object> obj = emailService.convertToObject(message);
        int user_id = (int) obj.get("user_id");
        String product_name = (String) obj.get("product_name");

        String response = this.webClient.get()
                .uri("/user/"+ String.valueOf(user_id))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Map<String, Object> user = emailService.convertToObject(response);

        String content = emailService.constructContent(product_name, (String) user.get("username"));
        emailService.sendEmail(content, (String) user.get("email"), "Notificação XPTO");
    }
}