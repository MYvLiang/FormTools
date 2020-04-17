package com.formtools.service;

import com.formtools.model.FillQuestionnaire;
import com.formtools.model.FillRegistry;

public interface FillQuestionnaireService {
    boolean insertFillQuestionnaire(String key, FillQuestionnaire fillQuestionnaire);
    FillRegistry getFillQuestionnaireFromCache(String key);
}
