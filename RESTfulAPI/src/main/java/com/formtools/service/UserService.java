package com.formtools.service;

import com.formtools.model.User;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */

public interface UserService {

    User getUser(Map<String, Object> map);

    boolean addUser(User user);
}
