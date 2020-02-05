package com.formtools;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.model.BuiltForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FormtoolsApplicationTests {

    @Autowired
    BuiltFormMapper builtFormMapper;

    @Test
    void testAddBuiltForm() {
        BuiltForm builtForm=new BuiltForm("222","111","biaojiegou");
        int n=builtFormMapper.addBuiltForm(builtForm);
        System.out.println("插入"+n+"行");
    }
    @Test
    void testGetBuiltForm(){
        BuiltForm builtForm=builtFormMapper.getBuiltForm("111");
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
        int n=builtFormMapper.updateBuiltForm(new BuiltForm("222","111","biaojiegou2"));
        System.out.println("更改："+n);
    }
    @Test
    void testDeleteBuiltForm(){
        int n=builtFormMapper.deleteBuiltForm("1");
        System.out.println(n);
    }
    @BeforeEach
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @AfterEach
    public void after() {
        System.out.println("测试结束-----------------");
    }


}
