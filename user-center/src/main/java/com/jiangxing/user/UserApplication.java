package com.jiangxing.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 *
 * @author wyq
 */

@SpringBootApplication
//@EnableEurekaClient 与EnableDiscoveryClient作用相同
//但是建议以后使用discoveryClient,这样以后迁移注册中心不需要修改注解
@EnableDiscoveryClient
@EnableFeignClients
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }


//    /**
//     * 将restTemplate注册进iod容器
//     *
//     * @return
//     */
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        //单机redis配置类
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        //lettuce的redis工厂类，听说lettuce操作redis是线程安全的
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
        return factory;
    }
}
