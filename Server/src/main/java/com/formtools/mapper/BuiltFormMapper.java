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

    int addBuiltForm(BuiltForm builtForm);

    BuiltForm getBuiltForm(String formId);

    List<BuiltForm> findAllBuiltForm();

    int updateBuiltForm(BuiltForm builtForm);

    int deleteBuiltForm(String id);
}
