package com.yanki.wallet.service.api.repository;

import com.yanki.wallet.service.api.model.WalletModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends ReactiveMongoRepository<WalletModel, String> {
}
