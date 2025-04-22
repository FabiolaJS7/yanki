package com.yanki.wallet.service.api.consumer;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.service.WalletService;
import com.yanki.wallet.service.api.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class YankiWalletConsumer {

    @Autowired
    WalletService walletService;

    @KafkaListener(topics = "yanki-wallet", groupId = "user_group")
    public void createWallet(String message) {
        log.info("Creation wallet recepted: {}", message);
        WalletRequest walletRequest = JsonTransferUtil.jsonToObject(message, WalletRequest.class);

         walletService.createWallet(Single.just(walletRequest))
                 .doOnSuccess(walletResponse -> log.info("Wallet created: {}",
                         JsonTransferUtil.objectToJson(walletResponse)))
                 .subscribe();

    }
}
