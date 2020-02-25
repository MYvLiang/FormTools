package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.formtools.model.UserModel;
import com.formtools.enums.ErrorMsg;
import com.formtools.service.UserService;
import com.formtools.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
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

    /**
     * 登录检验用户输入的邮箱和密码，登录成功则以JSON格式返回用户所有信息，
     * 否则返回的用户信息都为null：
     * {
     * "userId": null,
     * "email": null,
     * "password": null,
     * "nickname": null
     * }
     *
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/user")
    public UserModel getUser(@RequestParam(value = "email", defaultValue = "VoV") String email,
                             @RequestParam(value = "password", defaultValue = "VoV") String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("password", password);
        UserModel userModel = userService.getUser(map);
        if (userModel != null) {
            if (email.equals(userModel.getEmail()) && password.equals(userModel.getPassword())) {
                return userModel;
            }
        }
        return new UserModel();
    }


    /**
     * 注册用户，需要用户的邮箱，密码，昵称，注册成功
     * {
     * "email": "email",
     * "password": "password",
     * "nickname": "nickname"
     * }
     * 注册成功则返回用户的所有信息
     * {
     * "userId": "userId",
     * "email": "email",
     * "password": "password",
     * "nickname": "nickname"
     * }
     * 否则为
     * {
     * "userId": null,
     * "email": null,
     * "password": null,
     * "nickname": null
     * }
     *
     * @param userMessage
     * @return
     */
    @PostMapping("/user")
    public UserModel getUser(@RequestBody(required = false) String userMessage) {
        try {
            JSONObject jsonObject = JSON.parseObject(userMessage);
            if (jsonObject != null) {
                String email = jsonObject.getString("email");
                String password = jsonObject.getString("password");
                String nickname = jsonObject.getString("nickname");
                if (email != null && password != null && nickname != null) {
                    long timeMillis = System.currentTimeMillis();
                    String userId = "userModel" + timeMillis;
                    UserModel userModel = new UserModel(userId, email, password, nickname," ");
                    if (userService.addUser(userModel)) {
                        return userModel;
                    }
                }
            }
        } catch (Exception e) {
        }
        return new UserModel();
    }
    @PutMapping("/user")
    public UserModel updateUser(@RequestBody(required = false) String userMessage, HttpSession httpSession){
        try {
            JSONObject jsonObject = JSON.parseObject(userMessage);
            if (jsonObject != null) {
                UserModel userModel =new UserModel();
                if(jsonObject.containsKey("email")){
                    userModel.setEmail(jsonObject.getString("email"));
                }
                if(jsonObject.containsKey("password")){
                    userModel.setEmail(jsonObject.getString("password"));
                }
                if(jsonObject.containsKey("nickname")){
                    userModel.setEmail(jsonObject.getString("nickname"));
                }
                UserModel currentUserModel =(UserModel)httpSession.getAttribute("current");

            }
        } catch (Exception e) {
        }
        return new UserModel();
    }

    @GetMapping("/email-code")
    public ResultVo sendEmailCode(@RequestParam("email") @Email String email){
        try {
            return userService.sendEmailCode(email);
        } catch (MessagingException e) {
            return ResultVo.fail(ErrorMsg.EMAIL_SEND_ERROR);
        }
    }
}