package com.formtools;

import com.formtools.mapper.FormDataMapper;
import com.formtools.model.FormData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author myl
 * @create 2020-02-05  17:50
 */
@SpringBootTest
class FormDataTests {

    @Resource
    FormDataMapper formDataMapper;

    @Test
    void testCreateForm(){
        int n=formDataMapper.createForm("form11111111");
        System.out.println(n);
    }
    @Test
    void testDeleteForm(){
        int n=formDataMapper.deleteForm("form11111111");
        System.out.println(n);
    }
    @Test
    void testAddFormData(){
        int n=formDataMapper.addFormData("form11111111","111","formdata");
        System.out.println(n);
    }
    @Test
    void testGetFormData(){
        FormData formData=formDataMapper.getFormData("form11111111","111");
        System.out.println(formData);
    }
    @Test
    void testFindAllFormData(){
        List<FormData> formDataList=formDataMapper.findAllFormData("form11111111");
        for(FormData formData:formDataList)
            System.out.println(formData);
    }
    @Test
    void testUpdataFormData(){
        int n=formDataMapper.updataFormData("form11111111","data2","111");
        System.out.println(n);
    }
    @Test
    void testDeleteFormData(){
        int n=formDataMapper.deleteFormData("form11111111","111");
        System.out.println(n);
    }
}
