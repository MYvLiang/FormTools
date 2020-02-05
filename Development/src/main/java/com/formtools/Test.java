package com.formtools;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.model.BuiltForm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author myl
 * @create 2020-02-05  14:35
 */
public class Test {

    @Autowired BuiltFormMapper builtFormMapper;

    public static void main(String[] args) {
        BuiltForm builtForm=new BuiltForm("111","111","biaojiegou");
        Test test=new Test();
        int n=test.builtFormMapper.addBuiltForm(builtForm);
        System.out.println("插入"+n+"个表");
    }
}
