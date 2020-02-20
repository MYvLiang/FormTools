package com.formtools.mapper;

import com.formtools.model.BuiltForm;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 增删查改用户创建的表单
 * @author myl
 * @create 2020-02-05  14:24
 */
@Mapper
public interface BuiltFormMapper {

    @Insert("insert into all_built_form" +
            "(form_id,builder_id,form_title,form_info," +
            "built_time,begin_time,end_time,max_count,form_state)" +
            " values " +
            "(#{formId},#{builderId},#{formTitle}," +
            "#{formInfo},#{builtTime},#{beginTime}," +
            "#{endTime},#{maxCount},#{formState})")
    int addBuiltForm(BuiltForm builtForm);

    BuiltForm getBuiltForm(String formId);

    List<BuiltForm> findAllBuiltForm();

    int updateBuiltForm(BuiltForm builtForm);

    @Delete("delete from all_built_form where form_id= #{formId}")
    int deleteBuiltForm(String id);
}
