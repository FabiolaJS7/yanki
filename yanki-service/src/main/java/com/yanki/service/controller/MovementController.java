package com.yanki.service.controller;

import com.yanki.service.bean.MovementRequest;
import com.yanki.service.service.MovementService;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yanki-service/movements")
@Slf4j
public class MovementController {

    @Autowired
    MovementService movementService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<String>> createMovement(@RequestBody MovementRequest movementRequest) {
        log.info("-> Init create movement RQ: {}", JsonTransferUtil.objectToJson(movementRequest));
        return movementService.createMovement(Single.just(movementRequest))
                .doOnSuccess(s -> log.info("Sending to create movement."))
                .map(ResponseEntity::ok)
                .onErrorReturn(e -> ResponseEntity.badRequest().build());
    }
}
