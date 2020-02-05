package com.formtools;

import com.formtools.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.formtools.model.User;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:33
 */
@SpringBootTest
public class UserMapperTests {

    @Resource
    UserMapper userMapper;

    @Test
    void addUserTest(){
        int n=userMapper.addUser(new User("333","1315646@163.com","123456","shiny"));
        System.out.println(n);
    }

    @Test
    void getUserTest(){
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userId", "111");
        map.put("email", "1315646@163.com");
//        map.put("password","123456");
        User user=userMapper.getUser(map);
        System.out.println(user);
    }
    @Test
    void updateUserTest(){
        User user=new User();
        user.setUserId("111");
//        user.setEmail("11111@gmail.com");
//        user.setPassword("11111111");
        user.setNickname("shiny");
        int n=userMapper.updateUser(user);
        System.out.println(n);
    }
}
