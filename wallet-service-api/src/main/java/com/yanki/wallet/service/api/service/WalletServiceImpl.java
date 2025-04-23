package com.yanki.wallet.service.api.service;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.constants.MovementTypeConstants;
import com.yanki.wallet.service.api.mapper.WalletMapper;
import com.yanki.wallet.service.api.model.WalletModel;
import com.yanki.wallet.service.api.repository.DaoWalletFactory;
import com.yanki.wallet.service.api.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    DaoWalletFactory daoWalletFactory;

    @Override
    public Single<WalletResponse> createOrUpdateWallet(Single<WalletRequest> walletRequest) {
        return walletRequest
                .flatMap(rq -> {
                    if (MovementTypeConstants.CREATE.equals(rq.getMovementType())) {
                        WalletModel newWallet = WalletMapper.INSTANCE.getWalletModelFromRequest(rq);
                        newWallet.setCreatedAt(LocalDate.now());
                        newWallet.setUpdatedAt(LocalDate.now());
                        return Single.just(newWallet);
                    } else {
                        return RxJava3Adapter.monoToSingle(daoWalletFactory.getWalletRepository().findWalletModelByPhoneAsWalletId(rq.getPhoneAsWalletId())
                                        .switchIfEmpty(Mono.error(new RuntimeException("Wallet not found for ID: " + rq.getPhoneAsWalletId()))))
                                .flatMap(walletModel -> {
                                    double newTotalAmount;
                                    if (MovementTypeConstants.DEPOSIT.equalsIgnoreCase(rq.getMovementType())) {
                                        log.info("Update wallet deposit");
                                        newTotalAmount = walletModel.getTotalAmount() + rq.getTotalAmount();
                                    } else {
                                        log.info("Update wallet without deposit");
                                        newTotalAmount = walletModel.getTotalAmount() - rq.getTotalAmount();
                                    }

                                    walletModel.setTotalAmount(newTotalAmount);
                                    walletModel.setUpdatedAt(LocalDate.now());
                                    return Single.just(walletModel);
                                });
                    }
                })
                .doOnSuccess(walletModel -> log.info("WalletModel to created or updated : {}", JsonTransferUtil.objectToJson(walletModel)))
                .flatMap(walletModel -> RxJava3Adapter.monoToSingle(daoWalletFactory.getWalletRepository().save(walletModel)))
                // Mapeando a WalletResponse
                .map(WalletMapper.INSTANCE::getWalletResponseFromWalletModel)
                .doOnSuccess(walletResponse -> log.info("Wallet created/updated successfully: {}",
                        JsonTransferUtil.objectToJson(walletResponse)))
                .doOnError(throwable -> log.error("Error creating yanki wallet: {}", throwable.getMessage()));

    }
}
