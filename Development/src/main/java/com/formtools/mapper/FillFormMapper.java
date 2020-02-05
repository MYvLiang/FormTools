package com.formtools.mapper;

import com.formtools.model.FillForm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 增加填表记录
 * 查找所填过的所有表
 * 删除某个表填的数据
 * @author myl
 * @create 2020-02-05  17:05
 */
@Mapper
public interface FillFormMapper {

    @Insert("insert into fill_form values(#{userId},#{formId})")
    int addFillForm(FillForm fillForm);

    @Select("select *from fill_form where userId=#{userId}")
    List<FillForm> findAllFillForm(String userId);

    @Delete("delete from fill_form where userId=#{userId} and formId=#{formId}")
    int deleteFillForm(String userId,String formId);
}
