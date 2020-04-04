package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.formtools.OtherConfig;
import com.formtools.enums.ErrorMsg;
import com.formtools.model.UserModel;
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


/**
 * 用户的账号操作
 */
@RestController
@Validated
public class UserController {

    //域名
    private static final String domainName="localhost:8080";


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
        Long userId=userService.emailLogin(email,password);
        if (userId==0)
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        //成功则设置cookie
        Cookie cookie=new Cookie("userId",String.valueOf(userId));
        cookie.setMaxAge(OtherConfig.cookieMaxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

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
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

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
            //注册
            return userService.sendEmailCode(email,"Z");
        } catch (MessagingException e) {
            return ResultVo.fail(ErrorMsg.EMAIL_SEND_ERROR);
        }
    }

    /**
     * 用户注册功能：验证码发送api
     * @param email
     * @return
     */
    @GetMapping("/email-code/reset-password")
    public ResultVo sendEmailCodeResetPassword(@RequestParam("email") @Email String email){
        try {
            //修改密码
            return userService.sendEmailCode(email,"X");
        } catch (MessagingException e) {
            return ResultVo.fail(ErrorMsg.EMAIL_SEND_ERROR);
        }
    }

    /**
     * 邮箱用户注册
     * @param message
     * @return
     */
    @PostMapping("/user")
    public ResultVo register(@RequestBody String message){
        JSONObject jsonObject= null;
        try {
            jsonObject = JSON.parseObject(message);
        } catch (Exception e) {
            //参数解析错误
            return ResultVo.fail(ErrorMsg.JSON_READ_ERROR);
        }
        // 提取随注册表单提交的验证码 code
        // code在service有校验 此处不必
        String code=(String) jsonObject.get("code");
        /*
         * 注册信息包含：
         * 用户名 邮箱 密码
         */
        //转化未 userModel对象
        UserModel userModel=JSONObject.parseObject(message,UserModel.class);

        //参数校验
        validationUtil.validateParam(userModel,new Class[]{UserModel.register.class});

        if (userService.register(userModel,code)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.REGISTER_ERROR);
    }

    /**
     * 忘记密码 重设密码
     * @param message
     * @return
     */
    @PostMapping("reset-password")
    public ResultVo resetPassword(@RequestBody String message){
        JSONObject jsonObject;
        try {
            jsonObject = JSON.parseObject(message);
        } catch (Exception e) {
            //参数解析错误
            return ResultVo.fail(ErrorMsg.JSON_READ_ERROR);
        }

        String code=(String) jsonObject.get("code");
        String password=(String) jsonObject.get("password");
        String passwordAgain=(String) jsonObject.get("password_again");

        //两密码相等
        if (password!=null && password.equals(passwordAgain)){
            //转化为 userModel对象
            UserModel userModel=JSONObject.parseObject(message,UserModel.class);
            //参数校验
            validationUtil.validateParam(userModel,new Class[]{UserModel.resetPassword.class});

            if (userService.resetPassword(userModel,code)){
                return ResultVo.success();
            }
        }
        else {
            return ResultVo.fail(ErrorMsg.PASSWORD_IS_NOT_SAME);
        }
        return ResultVo.fail(ErrorMsg.PASSWORD_RESET_ERROR);
    }

    /**
     * 上传头像
     * @param uploadFile
     * @param id
     * @return 用户头像url
     */
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
                //返回图片储存地址
                String image=userService.keepImage(uploadFile,id);
                String file= "http://"+domainName+"/images/users/images/"+image;
                return ResultVo.success(file);
            } catch (IOException e) {
                return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
            }
        }
        return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }

    /**
     * 获取用户信息
     * @param id cookie所带userId
     * @return
     */
    @GetMapping("/user")
    public ResultVo getUser(@CookieValue("userId") @NotNull(message = "登录异常 请重新登录")
                            @NotEmpty(message = "登录异常 请重新登录")
                                    String id){
        return ResultVo.success(userService.getUserInfo(Long.parseLong(id)));
    }

    /**
     * 修改用户个人信息
     * @param id
     * @return
     */
    @PutMapping("/user")
    public  ResultVo updateUser(@CookieValue("userId") @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录")
                                         String id,
                                @RequestBody UserModel userModel){
        //参数校验
        validationUtil.validateParam(userModel,new Class[]{UserModel.updateUserInfo.class});
        userModel.setUserId(Long.parseLong(id));
        if (userService.updateUserInfo(userModel)) return ResultVo.success();
        return ResultVo.fail(ErrorMsg.ACCOUNT_NOT_EXIT);
    }
}