package com.yanki.users.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserModel {

    @Id
    private String id;
    private String identificationType;
    private String identificationNumber;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String imeiNumber;
    private String password;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
