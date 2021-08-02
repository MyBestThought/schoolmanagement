package com.yht.config;

import com.yht.component.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MyConfig
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/18 21:56
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/main.html").setViewName("main");
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //配置拦截路径和不拦截的资源
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/login","/webjars/**","classpath:/static/**","/getVerify");
            }
        };

        return adapter;
    }

}