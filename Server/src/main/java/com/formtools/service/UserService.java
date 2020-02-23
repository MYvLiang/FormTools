package com.formtools.service;

import com.formtools.model.UserModel;

import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */

public interface UserService {

    UserModel getUser(Map<String, Object> map);

    boolean addUser(UserModel userModel);
}
