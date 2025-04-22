package com.yanki.users.service.api.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "example-topic", groupId = "user_group")
    public void consume(String message) {
        System.out.println("Mensaje recibido: " + message);
        // Procesar el mensaje
    }
}
