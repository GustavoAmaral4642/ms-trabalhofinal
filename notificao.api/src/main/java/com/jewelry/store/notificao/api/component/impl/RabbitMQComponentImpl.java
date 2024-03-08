package com.jewelry.store.notificao.api.component.impl;

import com.jewelry.store.notificao.api.component.RabbitMQComponent;
import com.jewelry.store.notificao.api.service.impl.EmailServiceImpl;
import com.jewelry.store.notificao.api.service.impl.helper.BuildHtmlMessage;
import com.jewelry.store.notificao.api.service.impl.helper.ConvertToObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class RabbitMQComponentImpl implements RabbitMQComponent {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private ConvertToObject convertToObject;

    @Autowired
    private BuildHtmlMessage buildHtmlMessage;

    private final WebClient webClient;

    public RabbitMQComponentImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @RabbitListener(queues = "vendas_notification")
    public void handleMessage(String message) {

        Map<String, Object> obj = convertToObject.convert(message);
        int user_id = (int) obj.get("user_id");
        List<String> arrayItems = (List<String>) obj.get("vendasItems");

        String response = this.webClient.get()
                .uri("/user/"+ String.valueOf(user_id))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Map<String, Object> user = convertToObject.convert(response);

        String content = buildHtmlMessage.buildMessage(arrayItems, (String) user.get("username"));
        emailService.sendEmail(content, (String) user.get("email"), "Envio de notificação sobre compra de produto");
    }

}