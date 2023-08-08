package com.zhn.common.domain.response;


import lombok.Data;

import java.util.Date;

@Data
public class ResponseUser {
    private String id;
    private String username;
    private String password;
    private String tel;
    private String name;
    private String avatar;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    private String balance;

}
