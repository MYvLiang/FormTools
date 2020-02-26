package com.formtools.mapper;

import com.formtools.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * CRUD用户信息
 * @author myl
 * @create 2020-02-05  22:06
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user_info(user_id,user_email,user_password,user_nickname,user_profile) " +
            "values(#{userId},#{email},#{password},#{nickname},#{profile})")
    int addUser(UserModel userModel);

    UserModel getUser(Map<String, String> map);

    int updateUser(UserModel userModel);

}
