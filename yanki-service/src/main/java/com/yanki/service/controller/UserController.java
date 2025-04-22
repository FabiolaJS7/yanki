package com.yanki.service.controller;

import com.yanki.service.bean.WalletRequest;
import com.yanki.service.bean.YankiUserRequest;
import com.yanki.service.service.UserService;
import com.yanki.service.service.WalletService;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/yanki-service/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    WalletService walletService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<String>> createUser(@RequestBody YankiUserRequest yankiUserRequestSingle) {
        log.info("Create user RQ: {}", JsonTransferUtil.objectToJson(yankiUserRequestSingle));
        return userService.createUser(yankiUserRequestSingle)
                .flatMap(s -> {
                    WalletRequest walletRequest = new WalletRequest();
                    walletRequest.setUserId(yankiUserRequestSingle.getPhoneNumber());
                    walletRequest.setTotalAmount(0.00);
                    walletService.createWallet(walletRequest);
                    return Single.just(walletRequest);
                })
                .doOnSuccess(walletRequest -> log.info("Sending to create wallet."))
                .map(walletRequest -> ResponseEntity.created(URI.create("/yanki-service/users/"))
                        .body(walletRequest.getUserId()));

    }
}
