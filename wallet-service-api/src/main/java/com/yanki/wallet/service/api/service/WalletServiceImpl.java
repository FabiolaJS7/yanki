package com.yanki.wallet.service.api.service;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.mapper.WalletMapper;
import com.yanki.wallet.service.api.repository.DaoWalletFactory;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    DaoWalletFactory daoWalletFactory;

    @Override
    public Single<WalletResponse> createWallet(Single<WalletRequest> walletRequest) {
        return walletRequest
                .map(WalletMapper.INSTANCE::getWalletModelFromRequest)
                .doOnSubscribe(disposable -> log.info("Subscribed to yanki wallet request"))
                .flatMap(walletModel -> RxJava3Adapter.monoToSingle(daoWalletFactory.getWalletRepository().save(walletModel))
                        .doOnSubscribe(disposable -> log.info("Subscribed to yanki wallet respose"))
                        .map(WalletMapper.INSTANCE::getWalletResponseFromWalletModel)
                        .doOnSuccess(walletResponse -> log.info("Created yanki wallet respose")));
    }
}
