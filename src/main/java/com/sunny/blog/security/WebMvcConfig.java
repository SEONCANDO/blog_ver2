package com.sunny.blog.security;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setCharset("UTF-8");
        mustacheViewResolver.setContentType("text/html; charset=UTF-8");
        mustacheViewResolver.setPrefix("classpath:/templates/");
        mustacheViewResolver.setSuffix(".html");

        registry.viewResolver(mustacheViewResolver);
        //위에서 생성한 mustache 리졸버를 적용

    }
}
