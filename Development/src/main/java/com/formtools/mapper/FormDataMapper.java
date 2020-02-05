package com.formtools.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 新建一张表单
 *增加一条数据
 * 查找一条数据
 * 查找表单中所有收集的数据
 * 修改一条数据
 * 删除一条数据
 * 删除整张表
 * @author myl
 * @create 2020-02-05  17:29
 */
@Mapper
public interface FormDataMapper {

    @Update("create table ${formId} (userId varchar(30),data varchar(10000))")
    int createForm(String formId);


}
