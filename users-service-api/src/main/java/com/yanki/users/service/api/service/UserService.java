package com.yanki.users.service.api.service;

import com.yanki.users.service.api.bean.YankiUserRequest;
import com.yanki.users.service.api.bean.YankiUserResponse;
import io.reactivex.rxjava3.core.Single;

public interface UserService {

    Single<YankiUserResponse> createUser(Single<YankiUserRequest> yankiUserRequest);
}
