package com.formtools.service;

import com.formtools.model.DraftForm;

import java.util.List;

/**
 * @author myl
 * @create 2020-04-16  23:15
 */
public interface DraftFormService {

    List<DraftForm> findAllDraftForms(Long userId);

    boolean addDraftForm(DraftForm draftForm);

    DraftForm getDraftForm(Long userId,Long formId);

    void saveForm(DraftForm draftForm);

    void saveDraft(DraftForm draftForm);

    boolean deleteDraft(Long formId,Long userId);
}
