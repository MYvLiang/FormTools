package com.formtools.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 储存email验证码
 * @author NaNRailgun
 */
@Component
public class EmailCodeReservoir {

    //储存验证码的Map
    private static Map<String, Examiner> keepEmailCodeMap=new ConcurrentHashMap<>();

    public Examiner get(String key){
        return keepEmailCodeMap.get(key);
    }

    public void put(String key,Examiner examiner){
        keepEmailCodeMap.put(key,examiner);
    }

    public void remove(String key){
        keepEmailCodeMap.remove(key);
    }

}
