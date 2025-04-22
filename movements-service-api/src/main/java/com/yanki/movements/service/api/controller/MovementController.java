package com.yanki.movements.service.api.controller;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.bean.MovementResponse;
import com.yanki.movements.service.api.service.MovementService;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/yanki/movements")
@Slf4j
public class MovementController {

    @Autowired
    MovementService movementService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<MovementResponse>> createMovement(@RequestBody MovementRequest movementRequest) {

        return movementService.createMovement(Single.just(movementRequest))
                .map(movementResponse -> ResponseEntity.created(URI.create("/api/yanki/movements/" + movementResponse.getId()))
                        .body(movementResponse));
    }
}
