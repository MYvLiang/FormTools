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
}
