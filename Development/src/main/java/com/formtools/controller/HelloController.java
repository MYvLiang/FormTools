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
        List<BuiltForm> builtFormList=builtFormMapper.findAllBuiltForm();
        return "Hello Spring Boot:"+builtFormList;
    }
}
