package com.yanki.service.service;

import com.yanki.service.bean.MovementRequest;
import com.yanki.service.bean.YankiUserRequest;
import io.reactivex.rxjava3.core.Single;

public interface WalletService {

    void createWallet(Single<YankiUserRequest> yankiUserRequestSingle);
    void editWallet(Single<MovementRequest> movementRequest);
}
