package com.yanki.movements.service.api.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YankiMovementConsumerTest {

    @Autowired
    YankiMovementConsumer yankiMovementConsumer;

    @Test
    void shouldCreateMovement_whenSendMessage_thenSuccess() {
        String movement = "{\n" +
                "    \"walletIdTo\": \"987621116\" ,\n" +
                "    \"walletIdFrom\": \"987622316\",\n" +
                "    \"amount\": 20.00,\n" +
                "    \"movementType\":\"CREATE\"\n" +
                "}";

        yankiMovementConsumer.createMovement(movement);

    }
}