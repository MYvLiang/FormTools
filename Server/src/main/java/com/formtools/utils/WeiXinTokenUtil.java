package com.formtools.utils;

import com.formtools.OtherConfig;
import com.formtools.vo.WXToken;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-26  17:23
 */
public class WeiXinTokenUtil {

    private static RestTemplate restTemplate=RestTemplateConfig.getRestTemplate();
    private static String token="";
    private static int expires_in=-1;
    private static LocalDateTime time=LocalDateTime.now();

    private static final String GRANT_TYPE= OtherConfig.GRANT_TYPE;
    private static final String APPID=OtherConfig.APPID;
    private static final String SECRET=OtherConfig.SECRET;

    public static String getToken() {
        Duration duration=Duration.between(time,LocalDateTime.now());
//        System.out.println("duration="+duration.toMillis());
        if (duration.toMillis()<expires_in*1000&&(!token.equals(""))){
//            System.out.println("原token");
            return token;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type={grant_type}&appid={appid}&secret={secret}";
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", GRANT_TYPE);
        params.put("appid", APPID);
        params.put("secret", SECRET);
        WXToken wxToken = restTemplate.getForObject(url, WXToken.class, params);
//        System.out.println("wxToken="+wxToken);
        if(wxToken.getErrcode()==null||wxToken.getErrcode()==0){
            expires_in=wxToken.getExpires_in()-200;
            token=wxToken.getAccess_token();
            time=LocalDateTime.now();
        }else {
            expires_in=-1;
            token="";
        }
        return token;
    }
}
