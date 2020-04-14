package com.formtools;

import com.alibaba.fastjson.JSON;
import com.formtools.mapper.FillQuestionnaireMapper;
import com.formtools.model.FillQuestionnaire;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author myl
 * @create 2020-04-04  11:13
 */
@SpringBootTest
class FillQuestionnaireMapperTests {

    @Resource
    FillQuestionnaireMapper fillQuestionnaireMapper;

    @Test
    void testAdd(){
        FillQuestionnaire fillQuestionnaire=new FillQuestionnaire(1111L,
                JSON.parseObject("{}"),new Timestamp(System.currentTimeMillis()));
        int n= fillQuestionnaireMapper.addQuestionnaire(fillQuestionnaire);
        System.out.println(n);
    }

    @Test
    void testFindAllFilled(){
        List<FillQuestionnaire> fillQuestionnaireList=fillQuestionnaireMapper.findFilledQuestionnaire(1111L);
        for(FillQuestionnaire data:fillQuestionnaireList)
            System.out.println(data);
    }

    @Test
    void testDelete(){
        int n=fillQuestionnaireMapper.deleteFilledQuestionnaire(3);
        System.out.println(n);
    }
}
