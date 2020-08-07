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
//@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，用于构建bean定义，初始化Spring容器
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebMvcConfig implements WebMvcConfigurer {

//    @Value("${server.http.port}")
//    private int httpPort;

    @Autowired
    private UrlInterceptor urlInterceptor;

//    @Bean 表明这个方法产生一个Bean对象，需要交给Spring进行管理
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


/**
  * 过滤器vs拦截器
  * 拦截器只能对 Action (也就是 Controller )请求起作用,而过滤器则可以对几乎所有的请求起作用;
  * 拦截器可以获取 IOC 容器中的各个 Bean ,而过滤器就不行;
  * 拦截器功能更强大些,过滤器能做的事情,它都能做,而且可以在请求前,请求后执行,比较灵活;
 */

//    注册过滤器
    @Bean
    public FilterRegistrationBean<ParameterFilter> filter(){
        FilterRegistrationBean<ParameterFilter> registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new ParameterFilter());
        return registrationBean;
    }

//    1.实现WebMvcConfigurer接口； 2.重写addInterceptors()方法；3.注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
    }
}
