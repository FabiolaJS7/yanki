package com.yanki.wallet.service.api.mapper;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.model.WalletModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    WalletModel getWalletModelFromRequest(WalletRequest request);
    WalletResponse getWalletResponseFromWalletModel(WalletModel walletModel);

}
