package com.formtools.mapper;

import com.formtools.model.FillQuestionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author myl
 * @create 2020-04-04  10:51
 */
@Mapper
public interface FillQuestionnaireMapper {

    int addQuestionnaire(FillQuestionnaire fillQuestionnaire);

    List<FillQuestionnaire> findFilledQuestionnaire(Long formId);

    int deleteFilledQuestionnaire(Integer id);

    /**
     * 当 当前时间晚于开始时间 早于截止时间 人数符合 则插入
     * @param fillQuestionnaire 问卷答案
     * @return 插入信息条数（1||0
     */
    int insertFilledQuestionnaire(FillQuestionnaire fillQuestionnaire);
}
