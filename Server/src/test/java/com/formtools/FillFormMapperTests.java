package com.formtools;

import com.formtools.mapper.FillFormMapper;
import com.formtools.model.FillForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author myl
 * @create 2020-02-05  17:18
 */
@SpringBootTest
class FillFormMapperTests {

    @Resource
    FillFormMapper fillFormMapper;

    @Test
    void testAddFillForm() {
        int n = fillFormMapper.addFillForm(new FillForm("111", "444"));
        System.out.println(n);
    }

    @Test
    void testFindAllFillForm() {
        List<FillForm> fillForms = fillFormMapper.findAllFillForm("111");
        for (FillForm fillForm : fillForms)
            System.out.println(fillForm);
    }

    @Test
    void testDeleteFillForm() {
        int n = fillFormMapper.deleteFillForm("111", "444");
        System.out.println(n);
    }
}
