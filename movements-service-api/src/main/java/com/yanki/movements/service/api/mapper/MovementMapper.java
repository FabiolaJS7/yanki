package com.yanki.movements.service.api.mapper;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.bean.MovementResponse;
import com.yanki.movements.service.api.model.MovementModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    MovementModel getMovementModelFromMovementRequest(MovementRequest movementRequest);
    MovementResponse getMovementResponseFromMovementModel(MovementModel movementModel);
}
