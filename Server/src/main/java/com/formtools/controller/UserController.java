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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 用户的账号操作
 */
@RestController
@Validated
public class UserController {

    //储存用户登入信息
    private static ConcurrentHashMap<String, LocalDateTime> loginMap=new ConcurrentHashMap<>();

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private UserService userService;

    /**
     * 邮箱登录（请求参数类型为 formdata）
     * @param email
     * @param password
     * @param response
     * @return
     */
    @GetMapping("/login")
    public ResultVo emailLogin(@RequestParam("email") @NotEmpty @NotNull @Email String email,
                               @RequestParam("password") @NotEmpty @NotNull String password,
                               HttpServletResponse response){
        //账号验证失败
        String userId=userService.emailLogin(email,password);
        if (userId.equals(""))
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        //成功则设置cookie
        Cookie cookie=new Cookie("userId",userId);
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        //置入缓存 记录时间
        loginMap.put(userId,LocalDateTime.now());
        return ResultVo.success();
    }

    /**
     * 退出登录
     * @param id
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ResultVo logout(@CookieValue("userId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录")String id, HttpServletResponse response){
        Cookie cookie=new Cookie("userId",id);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        loginMap.remove(id);
        return ResultVo.success();
    }


    /**
     * 用户注册功能：验证码发送api
     * @param email
     * @return
     */
    @GetMapping("/email-code")
    public ResultVo sendEmailCode(@RequestParam("email") @Email String email){
        try {
            return userService.sendEmailCode(email);
        } catch (MessagingException e) {
            return ResultVo.fail(ErrorMsg.EMAIL_SEND_ERROR);
        }
    }

    /**
     * 用户注册
     * @param message
     * @return
     */
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
    public ResultVo upload(@RequestParam("uploadFile") MultipartFile uploadFile,
                           @CookieValue("userId") @NotNull(message = "登录异常 请重新登录")
                           @NotEmpty(message = "登录异常 请重新登录")
                                   String id){
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
                userService.keepImage(uploadFile,id);
                return ResultVo.success();
            } catch (IOException e) {
                return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
            }
        }
        return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }
}