package com.example.springboot.config;

import com.example.springboot.filter.ParameterFilter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebMvcConfig {

//    @Value("${server.http.port}")
//    private int httpPort;

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

    @Bean
    public FilterRegistrationBean<ParameterFilter> filter(){
        FilterRegistrationBean<ParameterFilter> registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new ParameterFilter());
        return registrationBean;
    }
}
