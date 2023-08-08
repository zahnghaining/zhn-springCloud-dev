package com.zhn.auth.feign;


import com.zhn.common.domain.User;
import com.zhn.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("zhn-system")
public interface AuthFeignSystem {
    @GetMapping("/user/byphone/{phone}")
    Result<User>  byphone(@PathVariable String phone);



}
