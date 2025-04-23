package com.yanki.wallet.service.api.repository;

import com.yanki.wallet.service.api.model.WalletModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WalletRepository extends ReactiveMongoRepository<WalletModel, String> {
    Mono<WalletModel> findWalletModelByPhoneAsWalletId(String phoneAsWalletId);
}
