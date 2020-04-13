package com.formtools.service.impl;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.mapper.DraftFormMapper;
import com.formtools.model.BuiltForm;
import com.formtools.service.BuiltFormService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author myl
 * @create 2020-04-13  16:13
 */
@Service
public class BuiltFormServiceImpl implements BuiltFormService {

    @Resource
    BuiltFormMapper builtFormMapper;

    @Resource
    DraftFormMapper draftFormMapper;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 发布表单，并删除草稿
     * @param builtForm
     * @return
     */
    @Transactional
    public boolean releaseForm(BuiltForm builtForm){
        int bn=builtFormMapper.addBuiltForm(builtForm);
        draftFormMapper.deleteDraftForm(builtForm.getFormId());
        redisTemplate.delete(builtForm.getFormId().toString()+builtForm.getUserId().toString());
        return bn==1;
    }
}
