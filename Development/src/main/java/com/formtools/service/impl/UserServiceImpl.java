package com.formtools.service.impl;

import com.formtools.mapper.UserMapper;
import com.formtools.service.UserService;
import com.formtools.model.User;
import com.formtools.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  23:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User getUser(Map<String, Object> map){
        return userMapper.getUser(map);
    }

    public boolean addUser(User user){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", user.getEmail());
        User haduser=getUser(map);
        int n=0;
        if(haduser==null&& MyUtils.isUserFormat(user)){
            n=userMapper.addUser(user);
        }
        if(n>0){
            return true;
        }
        return false;
    }
}
