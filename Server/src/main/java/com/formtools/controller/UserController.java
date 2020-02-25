package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.service.UserService;
import com.formtools.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.Map;


/**
 * @author myl
 * @create 2020-02-05  22:57
 */
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;
/*
    @Autowired
    private EmailCodeReservoir emailCodeReservoir;


    @Autowired
    private UserService userService;

    @GetMapping("/email-code")
    public String sendEmail(@RequestParam("email") @Email String email) throws MessagingException {

        Map<String,Object> tempMap=new HashMap<>();
        tempMap.put("email",email);

        User user=userService.getUser(tempMap);
        //用户已存在
        if (user!=null){
            return "";
        }
        //用户是否存在于缓存
        Examiner examiner=emailCodeReservoir.get("email");
        System.out.println(examiner);
        if (examiner!=null){
            //获取剩余时间
            String timeRemain=examiner.timeComputer(LocalDateTime.now());
            if (!timeRemain.equals("0")) return timeRemain;
        }

        //发送邮件，返回剩余时间
        String code=CodeUtil.createCode();
        EmailUtil.SendEmail(email, code);
        emailCodeReservoir.put(email,new Examiner(code,LocalDateTime.now()));
        return "60";
    }*/
    @GetMapping("/email-code")
    public ResultVo sendEmailCode(@RequestParam("email") @Email String email){
        try {
            return userService.sendEmailCode(email);
        } catch (MessagingException e) {
            return ResultVo.fail(ErrorMsg.EMAIL_SEND_ERROR);
        }
    }

}