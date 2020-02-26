package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.formtools.model.UserModel;
import com.formtools.enums.ErrorMsg;
import com.formtools.service.UserService;
import com.formtools.utils.ValidationUtil;
import com.formtools.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;


/**
 * @author myl
 * @create 2020-02-05  22:57
 */
@RestController
@Validated
public class UserController {

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private UserService userService;


    @GetMapping("/email-code")
    public ResultVo sendEmailCode(@RequestParam("email") @Email String email){
        try {
            return userService.sendEmailCode(email);
        } catch (MessagingException e) {
            return ResultVo.fail(ErrorMsg.EMAIL_SEND_ERROR);
        }
    }

    @PostMapping("/user")
    public ResultVo register(@RequestBody String message){
        JSONObject jsonObject=JSON.parseObject(message);
        //code在service有校验 此处不必
        String code=(String) jsonObject.get("code");
        UserModel userModel=JSONObject.parseObject(message,UserModel.class);

        //参数校验
        validationUtil.validateParam(userModel,new Class[]{UserModel.register.class});

        if (userService.register(userModel,code)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}