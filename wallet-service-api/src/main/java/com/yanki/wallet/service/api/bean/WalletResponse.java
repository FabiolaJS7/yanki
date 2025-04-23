package com.yanki.wallet.service.api.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {

    @Id
    private String id;
    private String phoneAsWalletId;
    private Double totalAmount;
    private String productIdAssociated;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
