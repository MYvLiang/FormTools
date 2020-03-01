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
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;


/**
 * 用户的账号操作
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

    @PostMapping("/upload")
    public ResultVo upload(@RequestParam("uploadFile") MultipartFile uploadFile){
                //文件不空
        if (uploadFile!=null && uploadFile.getOriginalFilename()!=null && !uploadFile.getOriginalFilename().equals("")){

            //获取文件全名称
            String file_name=uploadFile.getOriginalFilename();
            //获取文件后缀
            String file_type=file_name.substring(file_name.lastIndexOf("."));
            //若文件类型错误
            if (!file_type.equals(".jpg") && !file_type.equals(".png")){
                return ResultVo.fail(ErrorMsg.FILE_TYPE_ERROR);
            }

            try {
                //调用service保存
                userService.keepImage(uploadFile,"111");//<<<<<<<<<用户id由token获取？
                return ResultVo.success();
            } catch (IOException e) {
                return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
            }
        }
        return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }
}