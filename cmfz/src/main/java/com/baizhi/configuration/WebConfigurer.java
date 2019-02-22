package com.baizhi.configuration;

import com.baizhi.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug("进入配置拦截器"+registry.toString());
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/login");
    }

}
