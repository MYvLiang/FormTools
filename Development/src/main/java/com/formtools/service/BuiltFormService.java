package com.formtools.service;

import com.formtools.model.BuiltForm;

import java.util.List;

/**
 * @author myl
 * @create 2020-02-06  14:21
 */
public interface BuiltFormService {

    //新建一个表单，需要表单id、建表者id、储存表结构信息的JSON串，
    boolean addBuiltForm(BuiltForm builtForm);

    BuiltForm getBuiltForm(String id);

    List<BuiltForm> findAllBuiltForm();

    int updateBuiltForm(BuiltForm builtForm);

    int deleteBuiltForm(String id);
}
