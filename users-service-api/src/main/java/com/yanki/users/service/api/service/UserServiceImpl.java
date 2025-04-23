package com.yanki.users.service.api.service;

import com.yanki.users.service.api.bean.YankiUserRequest;
import com.yanki.users.service.api.bean.YankiUserResponse;
import com.yanki.users.service.api.constants.ResultConstants;
import com.yanki.users.service.api.mapper.UserMapper;
import com.yanki.users.service.api.model.UserModel;
import com.yanki.users.service.api.repository.DaoUserFactory;
import com.yanki.users.service.api.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    DaoUserFactory daoUserFactory;

    @Override
    public Single<YankiUserResponse> createUser(Single<YankiUserRequest> userRequest) {
        return userRequest
                .flatMap(yankiUserRequest -> {
                    UserModel userModel = UserMapper.INSTANCE.getUserModelFromYankiUserRequest(yankiUserRequest);
                    userModel.setCreatedAt(LocalDate.now());
                    userModel.setUpdatedAt(LocalDate.now());
                    return Single.just(userModel);
                })
                .doOnSubscribe(disposable -> log.info("Subscribed to yanki user request"))
                .flatMap(userModel -> RxJava3Adapter.monoToSingle(
                        daoUserFactory.getUserRepository().findUserModelByPhoneNumber(userModel.getPhoneNumber())
                                .flatMap(existingUser -> {
                                    // Si el usuario ya existe, devolvemos un error
                                    YankiUserResponse yankiUserResponse = new YankiUserResponse();
                                    yankiUserResponse.setResult(ResultConstants.ERROR);
                                    return Mono.just(yankiUserResponse);
                                })
                                .switchIfEmpty(
                                        // Si el usuario no existe, se guarda
                                        daoUserFactory.getUserRepository().save(userModel)
                                                .map(savedUser -> {
                                                    YankiUserResponse yankiUserResponse = UserMapper.INSTANCE.getYankiUserResponseFromUserModel(savedUser);
                                                    yankiUserResponse.setResult(ResultConstants.SUCCESS);
                                                    return yankiUserResponse;
                                                })
                                )
                ))
                .doOnSubscribe(disposable -> log.info("Subscribed to yanki user response"))
                .doOnSuccess(yankiUserResponse -> log.info("Yanki User Response: {}",
                        JsonTransferUtil.objectToJson(yankiUserResponse)));

    }
}

