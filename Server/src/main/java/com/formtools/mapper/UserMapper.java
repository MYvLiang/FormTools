package com.formtools.mapper;

import com.formtools.model.EmailVerify;
import com.formtools.model.UserInfo;
import com.formtools.model.UserModel;
import com.formtools.model.UserVerify;
import org.apache.ibatis.annotations.Mapper;

/**
 * CRUD用户信息
 * @author myl
 * @create 2020-02-05  22:06
 */
@Mapper
public interface UserMapper {

    int addUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(Long userId);

    int updateUserInfo(UserInfo userInfo);

    int addUserVerify(UserVerify userVerify);

    int updateUserVerify(UserVerify userVerify);

    UserVerify getUserVerify(String openid);

    EmailVerify getEmailVerify(String email);

    int addEmailVerify(EmailVerify emailVerify);

    int updateEmailVerify(EmailVerify emailVerify);
}
