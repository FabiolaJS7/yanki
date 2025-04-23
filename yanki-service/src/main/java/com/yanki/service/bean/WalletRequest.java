package com.yanki.service.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {

    private String phoneAsWalletId;
    private Double totalAmount;
    private String productIdAssociated;
    private String movementType;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
