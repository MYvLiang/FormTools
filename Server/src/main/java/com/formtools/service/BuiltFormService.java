package com.formtools.service;

import com.formtools.model.BuiltForm;
import com.formtools.model.DraftForm;

import java.util.List;

/**
 * @author myl
 * @create 2020-02-06  14:21
 */
public interface BuiltFormService {

    List<BuiltForm> findAllBuiltForms(Long userId);

    boolean releaseForm(BuiltForm builtForm);

    BuiltForm copyBuiltForm(Long formId);

    boolean deleteBuiltForm(Long formId,Long userId);
}
