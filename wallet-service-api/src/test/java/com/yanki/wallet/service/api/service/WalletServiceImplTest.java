package com.yanki.wallet.service.api.service;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("local")
class WalletServiceImplTest {

    @Autowired
    WalletServiceImpl walletService;

    @Test
    void createWallet() {

        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setUserId("qwqweqweqew");
        walletRequest.setTotalAmount(123.00);
        walletRequest.setProductIdAssociated("123123123123");
        walletRequest.setCreatedAt(LocalDate.now());
        walletRequest.setUpdatedAt(LocalDate.now());

        Single<WalletResponse> walletResponse = walletService.createWallet(Single.just(walletRequest));
        TestObserver<WalletResponse> testObserver = walletResponse.test();

        testObserver.onComplete();

    }
}