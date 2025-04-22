package com.yanki.wallet.service.api.controller;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.service.WalletService;
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
@RequestMapping("/api/yanki/wallets")
@Slf4j
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<WalletResponse>> createUser(@RequestBody WalletRequest yankiUserRequestSingle) {

        return walletService.createWallet(Single.just(yankiUserRequestSingle))
                .map(saveWallet -> ResponseEntity.created(URI.create("/api/yanki/wallets/" + saveWallet.getId()))
                        .body(saveWallet));

    }
}
