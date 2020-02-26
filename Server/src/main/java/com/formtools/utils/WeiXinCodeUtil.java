package com.formtools.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public static String getCodeURL(String scene) {
        //获取小程序码的业务......
        String token=WeiXinTokenUtil.getToken();
        System.out.println("token="+token);
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("scene", scene);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity(paramMap, headers);
        ResponseEntity<byte[]> response = restTemplate.postForEntity(url, entity, byte[].class);
        byte[] body = response.getBody();
//        System.out.println("body.length="+body.length);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String p = "";
        if (body!=null||body.length > 20000) {
            try {
                p = ResourceUtils.getURL("classpath:").getPath()
                        + "static/images/weixin/code"+scene+".jpg";
                try {
                    inputStream = new ByteArrayInputStream(body);
                    File file = new File(p);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    outputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] buf = new byte[1024];
                    while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                        outputStream.write(buf, 0, len);
                    }
                    outputStream.flush();
                    p="static/images/weixin/code"+scene+".jpg";
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return p;
    }
}
