package com.formtools.controller;
import com.formtools.mapper.BuiltFormMapper;
import com.formtools.model.BuiltForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired BuiltFormMapper builtFormMapper;

    @RequestMapping("/hello")
    public String hello() {
        BuiltForm builtForm=new BuiltForm("111","111","*********");
//        int n=builtFormMapper.addBuiltForm(builtForm);
//        BuiltForm builtForm=builtFormMapper.getBuiltForm("111");
        List<BuiltForm> builtFormList=builtFormMapper.findAllBuiltForm();
        builtFormMapper.updateBuiltForm(builtForm);
        return "Hello Spring Boot:"+builtFormList;
    }
}
