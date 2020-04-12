package com.formtools.service;

import com.formtools.model.BuiltForm;

public interface FillRegistryService {
    BuiltForm getFormInfo(Long formId);
}
