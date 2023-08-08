package com.zhn.auth.service;

import com.zhn.common.domain.User;
import com.zhn.common.domain.request.RequestUser;
import com.zhn.common.domain.response.RespJwt;
import com.zhn.common.result.Result;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    Result<RespJwt> login(RequestUser requestUser);

    Result<User> userinfo(HttpServletRequest request);
}
