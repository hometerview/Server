package com.ftw.hometerview.core.config;

import com.ftw.hometerview.core.interceptor.AuthorizationInterceptor;
import com.ftw.hometerview.core.util.SearchTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor())
            .addPathPatterns("/api/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SearchTypeConverter());
    }

}
