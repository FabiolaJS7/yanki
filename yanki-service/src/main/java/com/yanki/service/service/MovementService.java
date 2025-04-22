package com.yanki.service.service;

import com.yanki.service.bean.MovementRequest;
import io.reactivex.rxjava3.core.Single;

public interface MovementService {

    Single<String> createMovement(MovementRequest movementRequest);
}
