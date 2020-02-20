package com.formtools;

import com.formtools.mapper.FormDataMapper;
import com.formtools.model.FormData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author myl
 * @create 2020-02-05  17:50
 */
@SpringBootTest
class FormDataMapperTests {

    @Resource
    FormDataMapper formDataMapper;

    @Test
    void testCreateForm(){
        int n=formDataMapper.createForm("form222222222");
        System.out.println(n);
    }
    @Test
    void testDeleteForm(){
        int n=formDataMapper.deleteForm("form222222222");
        System.out.println(n);
    }
    @Test
    void testAddFormData(){
        int n=formDataMapper.addFormData("form222222222",new FormData("2222","{}",
                new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis())));
        System.out.println(n);
    }
    @Test
    void testGetFormData(){
        FormData formData=formDataMapper.getFormData("form222222222","1111");
        System.out.println(formData);
    }
    @Test
    void testFindAllFormData(){
        List<FormData> formDataList=formDataMapper.findAllFormData("form222222222");
        for(FormData formData:formDataList)
            System.out.println(formData);
    }
    @Test
    void testUpdateFormData(){
        FormData formData=new FormData();
        formData.setUserId("1111");
        formData.setFillInfo("{修改}");
        formData.setAlterTime(new Timestamp(System.currentTimeMillis()));
        int n=formDataMapper.updateFormData("form222222222", formData);
        System.out.println(n);
    }
    @Test
    void testDeleteFormData(){
        int n=formDataMapper.deleteFormData("form222222222","2222");
        System.out.println(n);
    }
}
