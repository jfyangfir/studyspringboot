package com.example.springboot.config;

import com.example.springboot.filter.ParameterFilter;
import com.example.springboot.interceptor.UrlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebMvcConfig implements WebMvcConfigurer {

//    @Value("${server.http.port}")
//    private int httpPort;

    @Autowired
    private UrlInterceptor urlInterceptor;

//    @Bean
//    public Connector connector(){
//        Connector connector=new Connector();
//        connector.setScheme("http");
//        connector.setPort(httpPort);
//        return connector;
//    }

//    @Bean
//    public ServletWebServerFactory servletWebServerFactory(){
//        TomcatServletWebServerFactory factory=new TomcatServletWebServerFactory();
//        factory.addAdditionalTomcatConnectors(connector());
//        return factory;
//    }

//■过滤器vs拦截器
//■拦截器只能对Action (也就是Controller )请求起作用,而过滤器则可以对几乎所有的请求起作用;
//■拦截器可以获取IOC容器中的各个Bean ,而过滤器就不行;
//■拦截器功能更强大些, Filter能做的事情,它都能做,而且可以在请求前,请求后执行,比较灵活;
    @Bean
    public FilterRegistrationBean<ParameterFilter> filter(){
        FilterRegistrationBean<ParameterFilter> registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new ParameterFilter());
        return registrationBean;
    }

    //将拦截器注册进去
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
    }
}
