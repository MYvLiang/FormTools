package com.formtools;

import com.formtools.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author myl
 * @create 2020-02-19  15:39
 */
@SpringBootTest
public class SendEmailTest {

    @Test
    void testSendEmail(){
        try{
            System.out.println(EmailUtil.SendEmail("1739845021@qq.com","123456"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
