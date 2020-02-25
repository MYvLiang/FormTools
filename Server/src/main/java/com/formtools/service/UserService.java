package com.formtools.service;

import com.formtools.model.User;
import com.formtools.vo.ResultVo;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */

public interface UserService {

    User getUser(Map<String, Object> map);

    boolean addUser(User user);

    ResultVo sendEmailCode(String email) throws MessagingException;
}
