package com.formtools.service;


import com.formtools.model.UserInfo;
import com.formtools.model.UserModel;
import com.formtools.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */

public interface UserService {

    UserInfo getUserInfo(Long userId);
    ResultVo sendEmailCode(String email) throws MessagingException;
    boolean addUser(UserModel userModel);
    boolean isTrueCode(UserModel userModel,String code);
    boolean register(UserModel userModel,String code);
    String keepImage(MultipartFile multipartFile, String id) throws IOException;
    Long emailLogin(String email,String password);
}
