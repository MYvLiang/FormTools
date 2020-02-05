package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.formtools.model.User;
import com.formtools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user")
    public User getUser(@RequestParam(value = "email", defaultValue = "0") String email,
                          @RequestParam(value = "password", defaultValue = "0") String password) {
        System.out.println(password);
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

    @PostMapping("/user")
    public User getUser(@RequestBody String userMessage){
        JSONObject jsonObject= JSON.parseObject(userMessage);
        long timeMillis= System.currentTimeMillis();
        String userId="f"+timeMillis;
        String email=jsonObject.getString("email");
        String password=jsonObject.getString("password");
        String nickname=jsonObject.getString("nickname");
        if(email!=null&&password!=null&&nickname!=null){
            User user=new User(userId,email,password,nickname);
            if(userService.addUser(user)){
                return user;
            }
        }
        return new User();
    }
}