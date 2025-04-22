package com.yanki.service.controller;

import com.yanki.service.service.UserService;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/yanki-service/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<String>> createUser(@RequestBody String yankiUserRequestSingle) {

        return userService.createUser(yankiUserRequestSingle)
                .map(savedUser -> ResponseEntity.created(URI.create("/api/yanki/users/"))
                        .body(savedUser));

    }
}
