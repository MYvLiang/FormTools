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

    /**
     * 若 此刻时间 晚于开始时间 早于截止时间 总填表人数小于最大填表人数 插入数据
     * @param fillRegistry  数据
     * @return 插入数据条数
     */
    int insertFilledRegistry(FillRegistry fillRegistry);
}
