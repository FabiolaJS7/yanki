package com.yanki.movements.service.api.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequest {

    private String walletIdTo;
    private String walletIdFrom;
    private Double amount;
    private String movementType;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
