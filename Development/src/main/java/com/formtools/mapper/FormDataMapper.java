package com.formtools.mapper;

import com.formtools.model.FormData;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 新建一张表单
 * 删除整张表
 * 增加一条数据
 * 查找一条数据
 * 查找表单中所有收集的数据
 * 修改一条数据
 * 删除一条数据
 *
 * @author myl
 * @create 2020-02-05  17:29
 */
@Mapper
public interface FormDataMapper {

    @Update("create table if not exists ${formId} (userId varchar(30),data varchar(10000))")
    int createForm(String formId);

    @Update("drop table if exists ${formId}")
    int deleteForm(String formId);

    @Insert("insert into ${formId} values(#{userId},#{data})")
    int addFormData(String formId,String userId,String data);

    @Select("select * from ${formId} where userId=#{userId}")
    FormData getFormData(String formId,String userId);

    @Select("select * from ${formId}")
    List<FormData> findAllFormData(String formId);

    @Update("update ${formId} set data=#{data} where userId=#{userId}")
    int updataFormData(String formId,String data,String userId);

    @Delete("delete from ${formId} where userId=#{userId}")
    int deleteFormData(String formId,String userId);

}
