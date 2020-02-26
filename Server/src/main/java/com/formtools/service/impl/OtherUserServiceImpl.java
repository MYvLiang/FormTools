package com.formtools.service.impl;

import com.formtools.mapper.UserMapper;
import com.formtools.model.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-23  23:34
 */
@Service
public class OtherUserServiceImpl implements com.formtools.service.OtherUserService {
    @Resource
    private UserMapper userMapper;

    public boolean updateUser(UserModel userModel){
        Map<String, Object> map = new HashMap();
        map.put("userId", userModel.getUserId());
        UserModel hasUser =userMapper.getUser(map);
        int n=0;
        if(hasUser==null){
            n=userMapper.addUser(userModel);
        }else{
            n=userMapper.updateUser(userModel);
        }
        if(n==1)return true;
        return false;
    }
}
