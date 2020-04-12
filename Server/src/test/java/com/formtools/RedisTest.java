package com.formtools;

import com.formtools.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisTest {

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Test
    void test1(){
        UserModel userModel=new UserModel();
        userModel.setUserId(12L);
        redisTemplate.opsForValue().set("test",userModel);
    }

    @Test
    void test2(){
        System.out.println(redisTemplate.opsForValue().get("test"));
    }
}
