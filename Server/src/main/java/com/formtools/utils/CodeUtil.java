package com.formtools.utils;

public class CodeUtil {

    /**
     * @author NaNrailgun
     * 随机验证码生成
     * @return 验证码
     */
    public static String createCode(){
        StringBuffer stringBuffer=new StringBuffer(String.valueOf(System.currentTimeMillis()));
        return stringBuffer.substring(9);
    }
}
