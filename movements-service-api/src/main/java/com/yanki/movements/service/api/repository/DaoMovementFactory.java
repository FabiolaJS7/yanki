package com.yanki.movements.service.api.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DaoMovementFactory {

    private final MovementRepository movementRepository;

    public MovementRepository getMovementRepository() {
        return movementRepository;
    }
}
