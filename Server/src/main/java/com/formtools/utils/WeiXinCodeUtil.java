package com.formtools.utils;

import com.formtools.OtherConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-23  22:02
 */
public class WeiXinCodeUtil {

    private static RestTemplate restTemplate = RestTemplateConfig.getRestTemplate();

    public static byte[] getCode(String scene) {
        String token=WeiXinTokenUtil.getToken();
//        System.out.println("token="+token);
        String url = OtherConfig.CODE_URL + token;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("scene", scene);
//        paramMap.put("page",OtherConfig.LOGIN_PAGE);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity(paramMap, headers);
        ResponseEntity<byte[]> response = restTemplate.postForEntity(url, entity, byte[].class);
        return response.getBody();
    }
}
