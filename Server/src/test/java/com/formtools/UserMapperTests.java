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
//        int n=userMapper.addUser(new User("5555","222222222@163.com","123456","shiny","touxiang1"));
        User user=new User();
        user.setUserId("8888");
        user.setNickname("我我我");
        user.setProfile("/fdfd/dfd/a.jpg");
        int n=userMapper.addUser(user);
        System.out.println(n);
    }

    @Test
    void getUserTest(){
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userId", "11111111");
        map.put("email", "222222222@163.com");
        map.put("password","123456");
        User user=userMapper.getUser(map);
        System.out.println(user);
    }
    @Test
    void updateUserTest(){
        User user=new User();
        user.setUserId("11111");
        user.setEmail("11111@gmail.com");
        user.setPassword("11111111");
        user.setNickname("昵称昵称昵称");
        user.setProfile("头像");
        int n=userMapper.updateUser(user);
        System.out.println(n);
    }
}
