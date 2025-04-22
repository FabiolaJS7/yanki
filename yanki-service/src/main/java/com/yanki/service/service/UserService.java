package com.yanki.service.service;

import com.yanki.service.producer.KafkaProducer;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final KafkaProducer kafkaProducer;

    public UserService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public Single<String> createUser(String userData) {
        kafkaProducer.sendMessage("example-topic", userData);
        return Single.just(userData);
    }
}
