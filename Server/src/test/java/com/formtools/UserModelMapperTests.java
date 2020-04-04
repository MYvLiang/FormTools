package com.formtools;

import com.formtools.mapper.UserMapper;
import com.formtools.model.EmailVerify;
import com.formtools.model.UserInfo;
import com.formtools.model.UserModel;
import com.formtools.model.UserVerify;
import com.formtools.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author myl
 * @create 2020-02-05  22:33
 */
@SpringBootTest
public class UserModelMapperTests {

    @Resource
    UserMapper userMapper;

    @Resource
    UserServiceImpl userService;

    @Test
    void testaddUserInfo(){
        UserInfo userInfo=new UserInfo(111111111L,"***","***");
        int i=userMapper.addUserInfo(userInfo);
        System.out.println(i);
    }

    @Test
    void testgetUserInfo(){
        UserInfo userInfo=userMapper.getUserInfo(111111111L);
        System.out.println(userInfo);
    }

    @Test
    void testupdateUserInfo(){
        UserInfo userInfo=new UserInfo(111111111L,"222","***");
        int i=userMapper.updateUserInfo(userInfo);
        System.out.println(i);
    }

    @Test
    void testaddUserVerify(){
        UserVerify userVerify=new UserVerify(null,111111111L,"1111",'w');
        int i=userMapper.addUserVerify(userVerify);
        System.out.println(i);
    }

    @Test
    void testupdateUserVerify(){
        UserVerify userVerify=new UserVerify(null,111111111L,"11112",'w');
        int i=userMapper.updateUserVerify(userVerify);
    }

    @Test
    void testgetUserVerify(){
        UserVerify userVerify=userMapper.getUserVerify("11112");
        System.out.println(userVerify);
    }

    @Test
    void testaddEmailVerify(){
        EmailVerify emailVerify=new EmailVerify(null,"123","123");
        System.out.println(userMapper.addEmailVerify(emailVerify));
    }

    @Test
    void testgetEmailVerify(){
        EmailVerify emailVerify=userMapper.getEmailVerify("123");
        System.out.println(emailVerify);
    }
    @Test
    void testupdateEmailVerify(){
        EmailVerify emailVerify=userMapper.getEmailVerify("123");
        emailVerify.setEmail("1234");
        System.out.println(userMapper.updateEmailVerify(emailVerify));
        emailVerify=userMapper.getEmailVerify("1234");
        System.out.println(emailVerify);
    }
    @Test
    void updateUserInfoTest(){
        UserModel userModel=new UserModel();
        userModel.setProfile("111");
        userModel.setNickname("haha");
        userModel.setUserId(1585912422638L);

        System.out.println(userService.updateUserInfo(userModel));
        System.out.println();
    }
}
