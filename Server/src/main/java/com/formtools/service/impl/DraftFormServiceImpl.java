package com.formtools.service.impl;

import com.formtools.mapper.DraftFormMapper;
import com.formtools.model.DraftForm;
import com.formtools.service.DraftFormService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author myl
 * @create 2020-04-16  23:16
 */
@Service
public class DraftFormServiceImpl implements DraftFormService {

    @Resource
    DraftFormMapper draftFormMapper;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 查询所有草稿表单的简略信息
     * @param userId
     * @return
     */
    public List<DraftForm> findAllDraftForms(Long userId){
        List draftForms=draftFormMapper.findAllDraftForm(userId);
        return draftForms;
    }

    /**
     * 创建一个草稿
     * @param draftForm
     * @return
     */
    @Transactional
    public boolean addDraftForm(DraftForm draftForm){
        return draftFormMapper.addDraftForm(draftForm)==1;
    }

    /**
     * 获取草稿
     * @param userId
     * @param formId
     * @return
     */
    public DraftForm getDraftForm(Long userId,Long formId){
        DraftForm draftForm=draftFormMapper.getDraftForm(formId,userId);
        if(null==draftForm)return null;
        if(draftForm.getState()){
            DraftForm updataState=new DraftForm();
            updataState.setState(false);
            updataState.setFormId(draftForm.getFormId());
            draftFormMapper.updateDraftForm(updataState);
            redisTemplate.opsForValue().set(formId.toString()+userId.toString(),draftForm);
            return draftForm;
        } else {
            DraftForm draftForm1=(DraftForm) redisTemplate.opsForValue().get(formId.toString()+userId.toString());
            return null==draftForm1?draftForm:draftForm1;
        }
    }

    /**
     * 实时保存表单信息
     * @param draftForm
     */
    public void saveForm(DraftForm draftForm){
        redisTemplate.opsForValue().set(draftForm.getFormId().toString()+draftForm.getUserId().toString(),draftForm);
    }

    /**
     * 保存草稿到MYSQL
     * @param draftForm
     * @return
     */
    @Transactional
    public void saveDraft(DraftForm draftForm){
        if(draftFormMapper.updateDraftForm(draftForm)==0){
            draftFormMapper.addDraftForm(draftForm);
            redisTemplate.delete(draftForm.getFormId().toString()+draftForm.getUserId().toString());
        }
    }

    public boolean deleteDraft(Long formId,Long userId){
        redisTemplate.delete(formId.toString()+userId.toString());
        return draftFormMapper.deleteDraftForm(formId,userId)==1;
    }
}
