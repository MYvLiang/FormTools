package com.formtools.service;


import com.formtools.model.EmailVerify;
import com.formtools.model.UserInfo;
import com.formtools.model.UserModel;
import com.formtools.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-05  22:57
 */

public interface UserService {

    UserInfo getUserInfo(Long userId);
    ResultVo sendEmailCode(String email,String type) throws MessagingException;
    void addUser(UserModel userModel);
    boolean isTrueCode(UserModel userModel,String code,String type);
    boolean register(UserModel userModel,String code);
    String keepImage(MultipartFile multipartFile, String id) throws IOException;
    Long emailLogin(String email,String password);
    boolean resetPassword(UserModel userModel,String code);
    void updateEmailVerify(EmailVerify emailVerify);
    boolean updateUserInfo(UserModel userModel);
    Map getUserVerifyType(Long userId);
}
