package com.formtools;

import com.formtools.service.UserService;
import com.formtools.utils.CodeUtil;
import com.formtools.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author myl
 * @create 2020-02-19  15:39
 */
@SpringBootTest
public class SendEmailTest {

    @Autowired
    private UserService userService;

    @Test
    void testSendEmail(){
        try{
            System.out.println(EmailUtil.SendEmail("1739845021@qq.com","123456"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    void CodeTest(){
        System.out.println(CodeUtil.createCode());
    }

    @Test
    void CodeTest2(){
        System.out.println();
    }
}
