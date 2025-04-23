package com.yanki.service.service;

import com.yanki.service.bean.YankiUserRequest;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String YANKI_USER = "yanki-user";

    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    WalletService walletService;

    public Single<String> createUser(Single<YankiUserRequest> yankiUserRequestSingle) {
        return yankiUserRequestSingle
                .flatMap(yankiUserRequest -> {
                    kafkaProducer.sendMessage(YANKI_USER, JsonTransferUtil.objectToJson(yankiUserRequest));
                    walletService.createWallet(Single.just(yankiUserRequest));
                    return Single.just(yankiUserRequest.getPhoneNumber());
                })
                .doOnSuccess(s -> log.info("Sending to create user."))
                .doOnError(throwable -> log.error("Sending to create user error {}", throwable.getMessage()));

    }
}
