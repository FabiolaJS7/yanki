package com.yanki.users.service.api.consumer;

import com.yanki.users.service.api.bean.YankiUserRequest;
import com.yanki.users.service.api.service.UserService;
import com.yanki.users.service.api.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class YankiUserConsumer {

    @Autowired
    UserService userService;

    @KafkaListener(topics = "yanki-user", groupId = "user_group")
    public void createUser(String message) {
        log.info("User recibido: {}", message);
        YankiUserRequest yankiUserRequest = JsonTransferUtil.jsonToObject(message, YankiUserRequest.class);

         userService.createUser(Single.just(yankiUserRequest))
                 .doOnSuccess(yankiUserResponse -> log.info("User created: {}",
                         JsonTransferUtil.objectToJson(yankiUserResponse)))
                 .subscribe();

    }
}
