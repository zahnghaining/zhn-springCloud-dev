package com.zhn.system.service;

import com.zhn.common.domain.User;
import com.zhn.common.domain.request.RequestUser;
import com.zhn.common.result.Result;

public interface UserService {
    Result<User> byphone(String phone);

    Result updateUser(RequestUser requestUser);
}
