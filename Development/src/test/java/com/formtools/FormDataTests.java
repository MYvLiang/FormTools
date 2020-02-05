package com.formtools;

import com.formtools.mapper.FormDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author myl
 * @create 2020-02-05  17:50
 */
@SpringBootTest
public class FormDataTests {
    @Autowired
    FormDataMapper formDataMapper;

    @Test
    void testCreateForm(){
        int n=formDataMapper.createForm("form11111111");
        System.out.println(n);
    }
}
