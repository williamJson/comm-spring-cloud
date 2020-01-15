package com.jiangxing.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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


//    @Resource(name = "redisTemplate")
//    public ValueOperations<String, String> operations;


    @Test
    public void addValue() {
        //这他娘的，set没有返回值，也不知道成功与否，只能再get一下哈？？？
//        template.opsForValue().set("nihao", "nihao redis");
        // System.out.println(template.opsForValue().get("name"));
        //System.out.println(template.opsForValue().getAndSet("testsbsbs", "test"));
//        operations.set("ops", "ops");
//        System.out.println(operations.get("ops"));
//        System.out.println(template.execute(new RedisCallback<Boolean>() {
//            @Override
//            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//                return connection.set("1234aaa".getBytes(), "5667aaa".getBytes());
//            }
//        }));

    }



    @Test
    public void conditionOnClassArray() {
        System.out.println("start...");
    }

}
