package com.formtools.utils;

import com.formtools.Exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ValidationUtil {

    //SpringBoot能将Validator直接注入
    @Autowired
    private Validator validator;

    /**
     * 参数校验器
     * @param object 待校验对象
     * @param classes 分组
     */
    public void validateParam(Object object,Class[] classes){

        if (classes==null||classes.length==0) classes=new Class[]{Default.class};

        Set<ConstraintViolation<Object>> set = validator.validate(object, classes);
        //set.size大于0则表明参数出错
        if (set.size()>0){
            Map<String,String> map=new HashMap<>();
            for (ConstraintViolation<Object> cv : set){
                String[] param=cv.getPropertyPath().toString().split("\\.");
                String message=cv.getMessage();
                map.put(param[param.length-1],message);
            }
            //抛出参数异常，并且需要根据业务异常再写一个异常拦截器，此处不再赘述
            throw new ParamException(map);
        }
    }
}