package com.formtools.service;

import com.formtools.model.BuiltForm;
import com.formtools.model.FillRegistry;

public interface FillRegistryService {
    BuiltForm getFormInfo(Long formId);
    String currentSaveAnswer(String key, FillRegistry fillRegistry);
    FillRegistry getAnswer(Long userId, Long formId, String key);
    boolean insertRegistry(FillRegistry fillRegistry,String key);
}
