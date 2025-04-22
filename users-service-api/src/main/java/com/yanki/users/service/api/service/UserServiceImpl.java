package com.yanki.users.service.api.service;

import com.yanki.users.service.api.bean.YankiUserRequest;
import com.yanki.users.service.api.bean.YankiUserResponse;
import com.yanki.users.service.api.mapper.UserMapper;
import com.yanki.users.service.api.repository.DaoUserFactory;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    DaoUserFactory daoUserFactory;

    @Override
    public Single<YankiUserResponse> createUser(Single<YankiUserRequest> userRequest) {
        return userRequest
                .map(UserMapper.INSTANCE::getUserModelFromYankiUserRequest)
                .doOnSubscribe(disposable -> log.info("Subscribed to yanki user request"))
                .flatMap(userModel -> RxJava3Adapter.monoToSingle(daoUserFactory.getUserRepository().save(userModel))
                        .doOnSubscribe(disposable -> log.info("Subscribed to yanki user respose"))
                        .map(UserMapper.INSTANCE::getYankiUserResponseFromUserModel)
                        .doOnSuccess(yankiUserResponse -> log.info("Yanki User Response: {}", yankiUserResponse)));
    }
}
