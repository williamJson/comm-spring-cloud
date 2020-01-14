package com.jiangxing.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by wyq on 2020/1/13.
 *
 * @auther wyq
 * qq:342622023
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
public class RedisTest {


    @Autowired
    public RedisTemplate<String, String> template;


    @Resource(name = "redisTemplate")
    public ValueOperations<String, String> operations;

    @Test
    public void addValue() {
        //这他娘的，set没有返回值，也不知道成功与否，只能再get一下哈？？？
        //template.opsForValue().set("nihao", "nihao redis");
        System.out.println(template.opsForValue().get("name"));
//        operations.set("ops", "ops");
//        System.out.println(operations.get("ops"));

    }
}
