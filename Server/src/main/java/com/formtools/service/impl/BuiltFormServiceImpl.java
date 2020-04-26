package com.formtools.service.impl;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.mapper.DraftFormMapper;
import com.formtools.model.BuiltForm;
import com.formtools.model.DraftForm;
import com.formtools.service.BuiltFormService;
import com.formtools.utils.IdBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
     * 查询所有已创建发布的表单的简略信息
     * @param userId
     * @return
     */
    public List<BuiltForm> findAllBuiltForms(Long userId){
        List builtForms=builtFormMapper.findAllBuiltForm(userId);
        return builtForms;
    }

    /**
     * 复制表单
     * @param formId
     * @return
     */
    @Transactional
    public BuiltForm copyBuiltForm(Long formId){
        BuiltForm builtForm=builtFormMapper.getBuiltForm(formId);
        builtForm.setFormId(IdBuilder.getFormId());
        DraftForm draftForm=new DraftForm(builtForm,false);
        return draftFormMapper.addDraftForm(draftForm)==1?builtForm:null;
    }
    /**
     * 发布表单，并删除草稿
     * @param builtForm
     * @return
     */
    @Transactional
    public boolean releaseForm(BuiltForm builtForm){
        int bn=builtFormMapper.addBuiltForm(builtForm);
        draftFormMapper.deleteDraftForm(builtForm.getFormId(),builtForm.getUserId());
        redisTemplate.delete(builtForm.getFormId().toString()+builtForm.getUserId().toString());
        return bn==1;
    }

    public boolean deleteBuiltForm(Long formId,Long userId){
        redisTemplate.delete(formId.toString());
        return builtFormMapper.deleteBuiltForm(formId,userId)==1;
    }
}
