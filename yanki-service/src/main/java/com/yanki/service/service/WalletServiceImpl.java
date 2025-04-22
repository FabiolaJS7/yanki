package com.yanki.service.service;

import com.yanki.service.bean.WalletRequest;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public void createWallet(WalletRequest walletRequest) {
        kafkaProducer.sendMessage("yanki-wallet", JsonTransferUtil.objectToJson(walletRequest));
    }
}
