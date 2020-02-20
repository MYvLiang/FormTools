package com.formtools;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.model.BuiltForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class BuiltFormMapperTests {

    @Resource
    BuiltFormMapper builtFormMapper;

    @BeforeEach
    void init() {
        System.out.println("开始测试-----------------");
    }

    @AfterEach
    void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    void testAddBuiltForm() {
        BuiltForm builtForm=new BuiltForm();
        builtForm.setFormId("1111");
        builtForm.setBuilderId("11111111");
        builtForm.setFormTitle("标题");
        builtForm.setFormInfo("字段");
        builtForm.setBuiltTime(new Timestamp(System.currentTimeMillis()));
        builtForm.setBeginTime(new Timestamp(System.currentTimeMillis()));
//        builtForm.setEndTime(new Timestamp(System.currentTimeMillis()));
        builtForm.setFormState(false);
        int n=builtFormMapper.addBuiltForm(builtForm);
        System.out.println("插入"+n+"行");
    }
    @Test
    void testGetBuiltForm(){
        BuiltForm builtForm=builtFormMapper.getBuiltForm("2222");
        System.out.println(builtForm);
    }
    @Test
    void testFindAllBuiltForm(){
        List<BuiltForm> builtForms=builtFormMapper.findAllBuiltForm();
        for (BuiltForm builtForm:builtForms)
            System.out.println(builtForm);
    }

    @Test
    void testUpdateBuiltForm(){
        BuiltForm builtForm=new BuiltForm();
        builtForm.setFormId("1111");
        builtForm.setFormTitle("标题2");
        builtForm.setFormInfo("字段2");
        builtForm.setBuiltTime(new Timestamp(System.currentTimeMillis()));
        builtForm.setBeginTime(new Timestamp(System.currentTimeMillis()));
        builtForm.setEndTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(new Timestamp(System.currentTimeMillis()));
        builtForm.setMaxCount(500);
        builtForm.setFormState(true);
        int n=builtFormMapper.updateBuiltForm(builtForm);
        System.out.println("更改："+n);
    }
    @Test
    void testDeleteBuiltForm(){
        int n=builtFormMapper.deleteBuiltForm("3333");
        System.out.println(n);
    }

}
