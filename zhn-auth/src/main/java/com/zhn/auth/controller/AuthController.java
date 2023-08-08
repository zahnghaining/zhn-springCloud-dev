package com.zhn.auth.controller;


import com.zhn.auth.service.AuthService;
import com.zhn.common.domain.User;
import com.zhn.common.domain.request.RequestUser;
import com.zhn.common.domain.response.RespJwt;
import com.zhn.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    Result<RespJwt> login(@RequestBody RequestUser requestUser){

        return authService.login(requestUser);
    }


    @GetMapping("/user/info")
    Result<User> userinfo(HttpServletRequest request){
        return  authService.userinfo(request);

    }


}
