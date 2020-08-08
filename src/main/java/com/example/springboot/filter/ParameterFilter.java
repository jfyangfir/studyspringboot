package com.example.springboot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/** @WebFilter 用于将一个类声明为过滤器，该注解将会在部署时被容器处理，容器将根据具体的属性配置将相应的类部署为过滤器
  * (value、urlPatterns、servletNames 三者必需至少包含一个，且 value 和 urlPatterns 不能共存，如果同时指定，通常忽略 value 的取值)
  * /** ----代表所有，匹配多级目录,表示所有的方法都会进入ParameterFilter过滤器这个类*/
@WebFilter(filterName = "ParameterFilter",urlPatterns = "/**")
public class ParameterFilter implements Filter {

    private final static Logger LOGGER=LoggerFactory.getLogger(ParameterFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("ParameterFilter init.--------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("ParameterFilter doFilter.--------------------");
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
//        走不通
//        Map<String,String[]> maps=httpServletRequest.getParameterMap();
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest){

            //替换 HttpServletRequest request 里的参数
            @Override
            public String getParameter(String name) {
                String value=httpServletRequest.getParameter(name);
                if(StringUtils.isNotBlank(value)&&value.contains("fuck")){
                    return value.replace("fuck","***");
                }
                return super.getParameter(name);
            }

            //替换 @RequestParam String key 里的参数
            @Override
            public String[] getParameterValues(String name) {
                String[] values=httpServletRequest.getParameterValues(name);
                for(int i=0;i<values.length;i++){
                    String temp=values[i];
                    if(StringUtils.isNotBlank(temp)&&temp.contains("fuck")){
                        values[i]=temp.replaceAll("fuck","***");
                    }
                }
                return values;
            }
        };

        /*
         *过滤器的作用之一就是在用户的请求到达 servlet 之前，拦截下来做预处理，
         *处理之后便执行 chain.doFilter(request, response) 这个方法，
         *如果还有别的过滤器，那么将处理好的请求传给下个过滤器，
         *依此类推，当所有的过滤器都把这个请求处理好了之后，再将处理完的请求发给servlet
         **/
        filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.debug("ParameterFilter destroy.--------------------");
    }
}
