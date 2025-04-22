package com.yanki.wallet.service.api.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DaoWalletFactory {

    private final WalletRepository walletRepository;

    public WalletRepository getWalletRepository() {
        return walletRepository;
    }
}
