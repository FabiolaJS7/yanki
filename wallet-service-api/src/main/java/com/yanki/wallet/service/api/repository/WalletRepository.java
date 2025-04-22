package com.yanki.wallet.service.api.repository;

import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.model.WalletModel;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends ReactiveMongoRepository<WalletModel, String> {
    Single<WalletResponse> findWalletModelByUserId(String userId);
}
