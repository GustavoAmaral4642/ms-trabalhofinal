package com.jewelry.store.notificao.api.component;

public interface RabbitMQComponent {
    void handleMessage(String message);
}