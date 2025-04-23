package com.yanki.movements.service.api.consumer;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.service.MovementService;
import com.yanki.movements.service.api.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class YankiMovementConsumer {

    @Autowired
    MovementService movementService;

    @KafkaListener(topics = "yanki-movement", groupId = "user_group")
    public void createMovement(String message) {
        log.info("Creation movement arrived: {}", message);
        MovementRequest movementRequest = JsonTransferUtil.jsonToObject(message, MovementRequest.class);

        movementService.createMovement(Single.just(movementRequest))
                 .doOnSuccess(walletResponse -> log.info("movement saved: {}",
                         JsonTransferUtil.objectToJson(walletResponse)))
                 .subscribe();

    }
}
