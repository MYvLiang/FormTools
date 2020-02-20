package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.formtools.model.User;
import com.formtools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */
@RestController
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
    public User getUser(@RequestParam(value = "email", defaultValue = "VoV") String email,
                        @RequestParam(value = "password", defaultValue = "VoV") String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("password", password);
        User user = userService.getUser(map);
        if (user != null) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return new User();
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
    public User getUser(@RequestBody(required = false) String userMessage) {
        try {
            JSONObject jsonObject = JSON.parseObject(userMessage);
            if (jsonObject != null) {
                String email = jsonObject.getString("email");
                String password = jsonObject.getString("password");
                String nickname = jsonObject.getString("nickname");
                if (email != null && password != null && nickname != null) {
                    long timeMillis = System.currentTimeMillis();
                    String userId = "user" + timeMillis;
                    User user = new User(userId, email, password, nickname," ");
                    if (userService.addUser(user)) {
                        return user;
                    }
                }
            }
        } catch (Exception e) {
        }
        return new User();
    }
    @PutMapping("/user")
    public User updateUser(@RequestBody(required = false) String userMessage, HttpSession httpSession){
        try {
            JSONObject jsonObject = JSON.parseObject(userMessage);
            if (jsonObject != null) {
                User user=new User();
                if(jsonObject.containsKey("email")){
                    user.setEmail(jsonObject.getString("email"));
                }
                if(jsonObject.containsKey("password")){
                    user.setEmail(jsonObject.getString("password"));
                }
                if(jsonObject.containsKey("nickname")){
                    user.setEmail(jsonObject.getString("nickname"));
                }
                User currentUser=(User)httpSession.getAttribute("current");

            }
        } catch (Exception e) {
        }
        return new User();
    }
}