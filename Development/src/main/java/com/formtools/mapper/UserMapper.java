package com.formtools.mapper;

import com.formtools.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * CRUD用户信息
 * @author myl
 * @create 2020-02-05  22:06
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user values(#{userId},#{email},#{password},#{nickname})")
    int addUser(User user);

    User getUser(Map<String, Object> map);

    int updateUser(User user);

}
