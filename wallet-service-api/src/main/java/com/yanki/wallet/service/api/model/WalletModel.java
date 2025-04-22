package com.yanki.wallet.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "wallets")
public class WalletModel {

    @Id
    private String id;
    private String userId;
    private Double totalAmount;
    private String productIdAssociated;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
