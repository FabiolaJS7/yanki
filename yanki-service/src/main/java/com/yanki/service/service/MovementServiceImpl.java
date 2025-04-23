package com.yanki.service.service;

import com.yanki.service.bean.MovementRequest;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService {

    private static final String TOPIC_YANKI_MOVEMENT = "yanki-movement";

    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    WalletService walletService;

    @Override
    public Single<String> createMovement(Single<MovementRequest> movementRequest) {
        walletService.editWallet(movementRequest);
        kafkaProducer.sendMessage(TOPIC_YANKI_MOVEMENT, JsonTransferUtil.objectToJson(movementRequest));
        return Single.just("created");
    }
}
