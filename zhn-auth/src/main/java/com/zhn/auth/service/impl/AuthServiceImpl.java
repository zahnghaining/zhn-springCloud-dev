package com.zhn.auth.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.zhn.auth.feign.AuthFeignSystem;
import com.zhn.auth.service.AuthService;
import com.zhn.common.constants.JwtConstants;
import com.zhn.common.constants.TokenConstants;
import com.zhn.common.domain.User;
import com.zhn.common.domain.request.RequestUser;
import com.zhn.common.domain.response.RespJwt;
import com.zhn.common.result.Result;
import com.zhn.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;


import com.zhn.auth.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthFeignSystem authFeignSystem;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public Result<RespJwt> login(RequestUser requestUser) {
        String phone = requestUser.getPhone();
        String password = requestUser.getPassword();
        if (null == phone || null == password) {
            return Result.error("联系管理员");
        }
        if ("".equals(phone) || "".equals(password)) {

            return Result.error("请输入手机号+密码");
        }
        Result<User> byphone = authFeignSystem.byphone(phone);
        User data = byphone.getData();
        if (null == data) {
            return Result.error("先注册手机号,在登录");
        }
        HashMap<String, Object> map = new HashMap<>();
        String userkey = UUID.randomUUID().toString().replaceAll("-", "");
        map.put(JwtConstants.USER_KEY, userkey);
        map.put(JwtConstants.DETAILS_USER_ID, data.getId());
        String token = JwtUtils.createToken(map);
        redisTemplate.opsForValue().set(
                TokenConstants.LOGIN_TOKEN_KEY + userkey,
                JSONObject.toJSONString(data),
                TokenConstants.EXPIRATION,
                TimeUnit.MINUTES
        );
        RespJwt respJwt = new RespJwt();
        respJwt.setToken(token);
        respJwt.setEidTime("720MIN");

        return Result.success(respJwt);
    }

    /*
     * 前台每次请求都会来到这里进行解析user
     *
     * */
    @Override
    public Result<User> userinfo(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.TOKEN);
        String userKey = JwtUtils.getUserKey(token);
        String s = redisTemplate.opsForValue().get(
                TokenConstants.LOGIN_TOKEN_KEY + userKey
        );
        User user = JSONObject.parseObject(s, User.class);
        return Result.success(user);
    }

}
