package com.formtools.service;


import com.formtools.vo.ResultVo;

import javax.mail.MessagingException;
import com.formtools.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */

public interface UserService {

    UserModel getUser(Map<String, String> map);
    ResultVo sendEmailCode(String email) throws MessagingException;
    boolean addUser(UserModel userModel);
    boolean isTrueCode(UserModel userModel,String code);
    boolean register(UserModel userModel,String code);
    boolean keepImage(MultipartFile multipartFile, String id) throws IOException;
    String emailLogin(String email,String password);
}
