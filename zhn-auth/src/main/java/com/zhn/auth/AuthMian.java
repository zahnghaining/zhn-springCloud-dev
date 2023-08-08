package com.zhn.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhang
 */
@SpringBootApplication
@EnableFeignClients
public class AuthMian {
    public static void main(String[] args) {
        SpringApplication.run(AuthMian.class,args);
    }
}
