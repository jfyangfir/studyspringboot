package com.example.springboot.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component //把普通java对象实例化到spring容器中
public class UrlInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER= LoggerFactory.getLogger(UrlInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("Interceptor PreHandle()=======================");
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("Interceptor PostHandle()=======================");

        if(modelAndView==null || modelAndView.getViewName().startsWith("redirect")){return;}

        String path=request.getServletPath();
        String template= (String) modelAndView.getModelMap().get("template");
        if(StringUtils.isBlank(template)){
            if(path.startsWith("/")){
                path=path.substring(1);
            }
//            toLowerCase()方法用于把字符串转换为小写
            modelAndView.getModelMap().addAttribute("template",path.toLowerCase());
        }

        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("Interceptor AfterCompletion()=======================");
    }
}
