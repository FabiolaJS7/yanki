package com.yanki.users.service.api.repository;

import com.yanki.users.service.api.model.UserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserModel, String> {
    Mono<UserModel> findUserModelByPhoneNumber(String phoneNumber);
}
