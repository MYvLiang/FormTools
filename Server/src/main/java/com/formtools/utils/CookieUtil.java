package com.formtools.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    /**
     * 从FormId Cookie 中获取缓存的key
     * @param request req
     * @param formId 表id
     * @return 缓存key
     */
    public static String getKeyFromFormIdCookie(HttpServletRequest request,String formId){
        String key = null;
        //遍历cookie 获取名为formId的cookie 内含缓存的key
        Cookie[] cookies = request.getCookies();
        if (cookies==null || cookies.length==0) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(formId)) {
                key = cookie.getValue();
                break;
            }
        }
        return key;
    }
}
