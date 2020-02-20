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

    @Update("create table if not exists ${formId} (" +
            "user_id varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '填表者的用户id'," +
            "fill_info mediumtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '填表者所填写的信息'," +
            "fill_time datetime NOT NULL COMMENT '填表时间'," +
            "alter_time datetime NOT NULL COMMENT '最后一次修改的时间（默认为填表时间）'," +
            "PRIMARY KEY (user_id))")
    int createForm(String formId);

    @Update("drop table if exists ${formId}")
    int deleteForm(String formId);

    @Insert("insert into ${formId} " +
            "(user_id,fill_info,fill_time,alter_time)" +
            " values(#{formData.userId},#{formData.fillInfo},#{formData.fillTime},#{formData.alterTime})")
    int addFormData(String formId, FormData formData);

    FormData getFormData(@Param("formId")String formId, @Param("userId")String userId);

    List<FormData> findAllFormData(String formId);

    int updateFormData(@Param("formId")String formId, @Param("formData")FormData formData);

    @Delete("delete from ${formId} where user_id=#{userId}")
    int deleteFormData(String formId, String userId);

}
