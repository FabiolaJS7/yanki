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
public class YankiUserResponse {

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
