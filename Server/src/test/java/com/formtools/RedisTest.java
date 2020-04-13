package com.formtools;

import com.formtools.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

    @Test
    void test3(){
        List list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add("haha");
        redisTemplate.opsForValue().set("key",list);
    }
}
