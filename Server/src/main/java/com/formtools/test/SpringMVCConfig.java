package com.formtools.test;

/**
 * 启用跨域配置
 * 编写SpringMVCConfig类使用FilterConfig中的配置
 * @author Administrator
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private FilterConfig filterConfig;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(filterConfig).addPathPatterns("/**");
    }
}