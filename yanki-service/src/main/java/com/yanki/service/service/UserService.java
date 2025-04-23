package com.yanki.service.service;

import com.yanki.service.bean.YankiUserRequest;
import io.reactivex.rxjava3.core.Single;

public interface UserService {

    Single<String> createUser(Single<YankiUserRequest> userData);
}
