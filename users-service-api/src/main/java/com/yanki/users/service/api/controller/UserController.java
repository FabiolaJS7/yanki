package com.yanki.users.service.api.controller;

import com.yanki.users.service.api.bean.YankiUserRequest;
import com.yanki.users.service.api.bean.YankiUserResponse;
import com.yanki.users.service.api.service.UserService;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/api/yanki/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<YankiUserResponse>> createUser(@RequestBody YankiUserRequest yankiUserRequestSingle) {

        return userService.createUser(Single.just(yankiUserRequestSingle))
                .map(savedUser -> ResponseEntity.created(URI.create("/api/yanki/users/" + savedUser.getId()))
                        .body(savedUser));

    }
}
