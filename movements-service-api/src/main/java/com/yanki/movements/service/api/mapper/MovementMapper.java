package com.yanki.movements.service.api.mapper;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.bean.MovementResponse;
import com.yanki.movements.service.api.model.MovementModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    @Mapping(source = "walletIdTo", target = "walletIdTo")
    @Mapping(source = "walletIdFrom", target = "walletIdFrom")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "movementType", target = "movementType")
    MovementModel getMovementModelFromMovementRequest(MovementRequest movementRequest);

    @Mapping(source = "walletIdTo", target = "walletIdTo")
    @Mapping(source = "walletIdFrom", target = "walletIdFrom")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "movementType", target = "movementType")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    MovementResponse getMovementResponseFromMovementModel(MovementModel movementModel);
}
