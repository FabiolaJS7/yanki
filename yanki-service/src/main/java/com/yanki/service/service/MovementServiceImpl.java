package com.yanki.service.service;

import com.yanki.service.bean.MovementRequest;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovementServiceImpl implements MovementService {

    private static final String TOPIC_YANKI_MOVEMENT = "yanki-movement";

    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    WalletService walletService;

    @Override
    public Single<String> createMovement(Single<MovementRequest> movementRequest) {

        return movementRequest
                .flatMap(rq -> {
                    walletService.editWallet(Single.just(rq));
                    kafkaProducer.sendMessage(TOPIC_YANKI_MOVEMENT, JsonTransferUtil.objectToJson(rq));
                    return Single.just("success");
                })
                .doOnSuccess(s -> log.info("Sending to create movement."))
                .doOnError(throwable -> log.error("Sending to create movement error {}", throwable.getMessage()));

    }
}
