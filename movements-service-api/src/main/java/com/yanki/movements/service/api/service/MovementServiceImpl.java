package com.yanki.movements.service.api.service;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.bean.MovementResponse;
import com.yanki.movements.service.api.mapper.MovementMapper;
import com.yanki.movements.service.api.model.MovementModel;
import com.yanki.movements.service.api.repository.DaoMovementFactory;
import com.yanki.movements.service.api.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;

import java.time.LocalDate;

@Service
@Slf4j
public class MovementServiceImpl implements MovementService {

    @Autowired
    DaoMovementFactory daoMovementFactory;

    @Override
    public Single<MovementResponse> createMovement(Single<MovementRequest> movementRequest) {
        return movementRequest
                .flatMap(rq -> {
                    log.info("Mapping movement request to movement model");
                    MovementModel  movementModel = MovementMapper.INSTANCE.getMovementModelFromMovementRequest(rq);
                    movementModel.setCreatedAt(LocalDate.now());
                    movementModel.setUpdatedAt(LocalDate.now());
                    return Single.just(movementModel);
                })
                .doOnSuccess(disposable -> log.info("MovementModel: {}", JsonTransferUtil.objectToJson(disposable)))
                .flatMap(movementModel -> RxJava3Adapter.monoToSingle(daoMovementFactory.getMovementRepository().save(movementModel))
                        .doOnSubscribe(disposable -> log.info("Subcribed to yanki movement response"))
                        .map(MovementMapper.INSTANCE::getMovementResponseFromMovementModel)
                        .doOnSuccess(movementResponse -> log.info("Movement created with id " + movementResponse.getId())));
    }
}
