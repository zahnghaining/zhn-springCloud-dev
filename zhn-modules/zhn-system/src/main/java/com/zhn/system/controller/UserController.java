package com.zhn.system.controller;


import com.zhn.common.domain.User;
import com.zhn.common.domain.request.RequestUser;
import com.zhn.common.result.Result;
import com.zhn.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者:zhn
 *  \/\n
 * 描述:用户控制层
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    @GetMapping("/byphone/{phone}")
    Result<User> byphone(@PathVariable String phone){

        return userService.byphone(phone);
    }


    @PostMapping("/updateUser")
    Result updateUser(@RequestBody RequestUser requestUser){

        return userService.updateUser(requestUser);
    }


}
