package com.formtools.controller;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.model.BuiltForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    @Resource
    BuiltFormMapper builtFormMapper;

    @RequestMapping("/hello")
    public String hello() {
        List<BuiltForm> builtFormList=builtFormMapper.findAllBuiltForm();
        return "Hello Spring Boot:"+builtFormList;
    }
}
