package com.yanki.service.service;

import com.yanki.service.bean.MovementRequest;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService{

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public Single<String> createMovement(MovementRequest movementRequest) {
        kafkaProducer.sendMessage("yanki-movement", JsonTransferUtil.objectToJson(movementRequest));
        return Single.just("created");
    }
}
