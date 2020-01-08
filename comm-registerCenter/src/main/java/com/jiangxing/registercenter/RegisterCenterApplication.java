package com.jiangxing.registercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心启动类
 *
 * @author wyq
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterCenterApplication {


    public static void main(String[] args) {
        SpringApplication.run(RegisterCenterApplication.class, args);
    }


}
