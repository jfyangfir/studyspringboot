package com.example.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {

    private final static Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);

    /**
     *关联在方法上的切点
     *第一个代表返回类型不限
     *第二个代表module下所有子包
     *第三个*代表所有类
     *第四个*代表所有方法
     * (..) 代表参数不限
     * Order 代表优先级，数字越小优先级越高
     */

    //设置切点
    @Pointcut("execution(public * com.example.springboot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){}

    @Before(value = "com.example.springboot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.debug("Before Controller========aspect======");
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER. debug("请求来源:"+ request.getRemoteAddr());
        LOGGER. debug("请求URL:"+ request.getRequestURL().toString());
        LOGGER. debug("请求方式:"+ request. getMethod());
        LOGGER. debug("响应方法:"+ joinPoint .getSignature().getDeclaringTypeName() + "."+
                 joinPoint.getSignature().getName());
        LOGGER. debug("请求参数:"+ Arrays.toString(joinPoint.getArgs()));

    }

    @After(value = "com.example.springboot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController(){
        LOGGER.debug("After Controller========aspect=======");
    }

    @Around(value = "com.example.springboot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("Around Controller========aspect=======");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

}
