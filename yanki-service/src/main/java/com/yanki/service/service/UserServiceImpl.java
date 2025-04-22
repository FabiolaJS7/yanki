package com.yanki.service.service;

import com.yanki.service.bean.YankiUserRequest;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    KafkaProducer kafkaProducer;

    public Single<String> createUser(YankiUserRequest userData) {
        kafkaProducer.sendMessage("yanki-user", JsonTransferUtil.objectToJson(userData));
        return Single.just("created");
    }
}
