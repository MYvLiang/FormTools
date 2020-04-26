package com.formtools;

import com.alibaba.fastjson.JSONObject;
import com.formtools.mapper.DraftFormMapper;
import com.formtools.model.DraftForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class DraftFormMapperTests {

    @Resource
    DraftFormMapper draftFormMapper;

    @BeforeEach
    void init() {
        System.out.println("开始测试-----------------");
    }

    @AfterEach
    void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    void testAddDraftForm() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("aa","aaa");
        DraftForm draftForm = new DraftForm();
        draftForm.setFormId(154688L);
        draftForm.setUserId(111112L);
        draftForm.setFormTitle("标题");
        draftForm.setFormInfo(jsonObject);
        draftForm.setBuiltTime(new Timestamp(System.currentTimeMillis()));
        draftForm.setBeginTime(new Timestamp(System.currentTimeMillis()));
        draftForm.setEndTime(new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10));
        draftForm.setMaxCount(100);
        draftForm.setFormType("B");
        draftForm.setState(false);
        int n = draftFormMapper.addDraftForm(draftForm);
        System.out.println("插入" + n + "行");
    }

    @Test
    void testGetDraftForm() {
        DraftForm draftForm = draftFormMapper.getDraftForm(666666L,1111L);
        System.out.println(draftForm);
    }

    @Test
    void testFindAllDraftForm() {
        List<DraftForm> draftForms = draftFormMapper.findAllDraftForm(111112L);
        for (DraftForm draftForm : draftForms)
            System.out.println(draftForm);
    }

    @Test
    void testUpdateDraftForm() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("aa","aaa");
        DraftForm draftForm = new DraftForm();
        draftForm.setFormId(666666L);
        draftForm.setUserId(111112L);
        draftForm.setFormTitle("标题");
        draftForm.setFormInfo(jsonObject);
        draftForm.setBuiltTime(new Timestamp(System.currentTimeMillis()));
        draftForm.setBeginTime(new Timestamp(System.currentTimeMillis()));
        draftForm.setEndTime(new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10));
        draftForm.setMaxCount(100);
        draftForm.setFormType("B");
        draftForm.setState(true);
        int n = draftFormMapper.updateDraftForm(draftForm);
        System.out.println("更改：" + n);
    }

    @Test
    void testDeleteBuiltForm() {
        int n = draftFormMapper.deleteDraftForm(22222L,11L);
        System.out.println(n);
    }

}
