package com.formtools.service.impl;

import com.formtools.mapper.UserMapper;
import com.formtools.model.UserModel;
import com.formtools.service.UserService;
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

    public UserModel getUser(Map<String, Object> map) {
        return userMapper.getUser(map);
    }

    public boolean addUser(UserModel userModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", userModel.getEmail());
        UserModel haduser = getUser(map);
        int n = 0;
        if (haduser == null && MyUtils.isUserFormat(userModel)) {
            n = userMapper.addUser(userModel);
        }
        if (n > 0) {
            return true;
        }
        return false;
    }

    public boolean updateUser(UserModel userModelMessage, UserModel userModel){
        String userId= userModelMessage.getUserId();
        int n=userMapper.updateUser(userModel);
        if(n>0)return true;
        return false;
    }
}
