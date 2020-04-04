package com.formtools.mapper;

import com.formtools.model.FillRegistry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author myl
 * @create 2020-04-04  14:01
 */
@Mapper
public interface FillRegistryMapper {

    int addRegistry(FillRegistry fillRegistry);

    FillRegistry getFilledRegistry(@Param("userId") Long userId, @Param("formId") Long formId);

    List<FillRegistry> findAllFilled(Long formId);

    int updateFilledRegistry(FillRegistry fillRegistry);

    int cancelFilledRegistry(@Param("userId") Long userId, @Param("formId") Long formId, @Param("now")Timestamp now);

    int deleteFilledRegistry(@Param("userId") Long userId, @Param("formId") Long formId);
}
