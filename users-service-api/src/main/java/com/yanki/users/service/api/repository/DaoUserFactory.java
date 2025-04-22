package com.yanki.users.service.api.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DaoUserFactory {

    private final UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
