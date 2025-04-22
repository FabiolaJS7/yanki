package com.yanki.movements.service.api.service;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.bean.MovementResponse;
import io.reactivex.rxjava3.core.Single;

public interface MovementService {

    Single<MovementResponse> createMovement(Single<MovementRequest> movementRequest);

}
