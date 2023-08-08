package com.zhn.common.domain.response;

import lombok.Data;

/**
 * @ClassName:RespJwt
 * @author:zhn
 * @create: 2023-08-03 09:01
 * @Description: 用户登录token返回实体类
 */
@Data
public class RespJwt {
    private String token;
    private String eidTime;
}
