package com.formtools;

import com.formtools.mapper.UserMapper;
import com.formtools.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:33
 */
@SpringBootTest
public class UserModelMapperTests {

    @Resource
    UserMapper userMapper;

    @Test
    void addUserTest(){
//        int n=userMapper.addUser(new UserModel("5555","222222222@163.com","123456","shiny","touxiang1"));
        UserModel userModel =new UserModel();
        userModel.setUserId("99999");
        userModel.setNickname("我我我");
        userModel.setProfile("/fdfd/dfd/a.jpg");
        int n=userMapper.addUser(userModel);
        System.out.println(n);
    }

    @Test
    void getUserTest(){
        Map<String, String> map = new HashMap();
        map.put("userId", "1111");
//        map.put("email", "222222222@163.com");
//        map.put("password","123456");
        UserModel userModel =userMapper.getUser(map);
        System.out.println(userModel);
    }
    @Test
    void updateUserTest(){
        UserModel userModel =new UserModel();
        userModel.setUserId("11111");
//        userModel.setEmail("11111@gmail.com");
//        userModel.setPassword("11111111");
        userModel.setNickname("昵称昵称");
        userModel.setProfile("头像2");
        int n=userMapper.updateUser(userModel);
        System.out.println(n);
    }
}
