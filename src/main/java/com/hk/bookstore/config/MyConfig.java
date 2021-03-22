package com.hk.bookstore.config;

import com.hk.bookstore.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {

        //为了拦截直接输入地址跳过登录访问bashboard,所以排除以下几个网址,拦截除登录界面的所有界面的访问
        // /**代表所有网页
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/asserts/css/*.css",
                "/asserts/img/*.svg",
                "/asserts/js/*.js",
                "/","/login","/loginb","/signup","/spsuccess","/adminlogin","/adminloginb",
                "/index","/literature","/exercisebooks","/novel","/success","/exam","/history","/lang","/manager","/literatureofyoung","/law","/computer","/ancientbook","/search",
                "/webjars/bootstrap/5.0.0-beta2/css/*.css"
        );
    }
}

