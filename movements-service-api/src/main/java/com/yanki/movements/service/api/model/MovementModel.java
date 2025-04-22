package com.yanki.movements.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movements")
public class MovementModel {

    @Id
    private String id;
    private String walletIdTo;
    private String walletIdFrom;
    private Double amount;
    private String movementType;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;
}
