package com.formtools.mapper;

import com.formtools.model.DraftForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author myl
 * @create 2020-04-13  12:01
 */
@Mapper
public interface DraftFormMapper {
    int addDraftForm(DraftForm draftForm);

    DraftForm getDraftForm(@Param("formId") Long formId, @Param("userId") Long userId);

    List<DraftForm> findAllDraftForm(Long userId);

    int updateDraftForm(DraftForm draftForm);

    int deleteDraftForm(@Param("formId") Long formId, @Param("userId") Long userId);
}
