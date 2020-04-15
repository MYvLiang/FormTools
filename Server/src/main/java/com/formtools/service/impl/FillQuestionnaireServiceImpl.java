package com.formtools.service.impl;

import com.formtools.mapper.FillQuestionnaireMapper;
import com.formtools.model.FillQuestionnaire;
import com.formtools.model.FillRegistry;
import com.formtools.service.FillQuestionnaireService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FillQuestionnaireServiceImpl implements FillQuestionnaireService {

    @Resource
    private FillQuestionnaireMapper fillQuestionnaireMapper;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 问卷类 插入答案
     * @param key 缓存key
     * @param fillQuestionnaire 答案
     * @return 是否成功
     */
    @Transactional
    public boolean insertFillQuestionnaire(String key, FillQuestionnaire fillQuestionnaire){
        if (fillQuestionnaireMapper.insertFilledQuestionnaire(fillQuestionnaire)>0){
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }

    /**
     * 从缓存取出问卷类缓存的答案
     * @param key 缓存key
     * @return 答案（key找不到即返回空
     */
    public FillRegistry getFillQuestionnaireFromCache(String key){
        FillRegistry fillQuestionnaire;
        try {
            fillQuestionnaire=(FillRegistry) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
           fillQuestionnaire=null;
        }
        return fillQuestionnaire;
    }
}
