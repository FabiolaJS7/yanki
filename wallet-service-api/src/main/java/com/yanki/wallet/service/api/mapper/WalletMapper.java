package com.yanki.wallet.service.api.mapper;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.model.WalletModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    @Mapping(source = "phoneAsWalletId", target = "phoneAsWalletId")
    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "productIdAssociated", target = "productIdAssociated")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    WalletModel getWalletModelFromRequest(WalletRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "phoneAsWalletId", target = "phoneAsWalletId")
    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "productIdAssociated", target = "productIdAssociated")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    WalletResponse getWalletResponseFromWalletModel(WalletModel walletModel);

}
