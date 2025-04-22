package com.yanki.movements.service.api.repository;

import com.yanki.movements.service.api.model.MovementModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<MovementModel, String> {
}
