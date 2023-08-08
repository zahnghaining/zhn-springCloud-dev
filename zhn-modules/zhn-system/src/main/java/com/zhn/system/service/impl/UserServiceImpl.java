package com.zhn.system.service.impl;


import com.zhn.common.domain.User;
import com.zhn.common.domain.request.RequestUser;
import com.zhn.common.result.Result;
import com.zhn.system.mapper.UserMapper;
import com.zhn.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Result<User> byphone(String phone) {
        User user = userMapper.byphone(phone);

        return Result.success(user);
    }

/*
* 对用户进行修改{权限,金额等!}
*
* */
    @Override
    public Result updateUser(RequestUser requestUser) {
        return null;
    }

}
