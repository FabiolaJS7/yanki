package com.yanki.wallet.service.api.service;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import io.reactivex.rxjava3.core.Single;

public interface WalletService {

    Single<WalletResponse> createWallet(Single<WalletRequest> walletRequest);
}
