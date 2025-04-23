package com.yanki.users.service.api.service;

import com.yanki.users.service.api.bean.YankiUserRequest;
import com.yanki.users.service.api.bean.YankiUserResponse;
import com.yanki.users.service.api.model.UserModel;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;


@SpringBootTest
@Profile("dev")
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void createUser() {
        YankiUserRequest yankiUserRequest = new YankiUserRequest();
        yankiUserRequest.setName("Yanki7778");
        yankiUserRequest.setEmail("yanki@yanki.com");
        yankiUserRequest.setPassword("123456");
        yankiUserRequest.setImeiNumber("12313123123123");

        UserModel userModel = new UserModel();
        userModel.setName("Yanki7778");
        userModel.setEmail("yanki@yanki.com");
        userModel.setPassword("123456");
        userModel.setImeiNumber("12313123123123");

        Single<YankiUserResponse> userResponseSingle = userService.createUser(Single.just(yankiUserRequest));
        //Single<UserModel> userResponseSingle = userRepository.save(userModel);
        //System.out.println("userResponseSingle = " + JsonTransferUtil.objectToJson(userResponseSingle));
        TestObserver<YankiUserResponse> testObserver = userResponseSingle.test();

        testObserver.onComplete();

    }
}