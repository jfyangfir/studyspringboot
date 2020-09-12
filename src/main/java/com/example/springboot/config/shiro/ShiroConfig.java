package com.example.springboot.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**配置shiro过滤器工厂
      *拦截权限
      *anon:匿名访问，无需登录
      *authc:登录后才能访问
      *user:登录过能访问
      *logout:登出
      *roles:角色过滤器
      *URL匹配风格
      * ?:匹配一个字符，如/admin?将匹配/admin1，但不匹配/admin或/admin/
      * *:匹配零个或多个字符串，如/admin*将匹配/admin或/admin123，但不匹配/admin/1
      * **:匹配路径中的零个或多个路径，如/admin/**将匹配/admin/a或/admin/a/b
      *方法名不能乱写，如果我们定义为别的名称，又没有添加注册过滤器的配置，那么shiro会加毂ShirolWebFilterConfiguration过滤器，
      *该过滤器会寻找shiroFilterFactoryBean，找不到会抛出异常
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/account/login");
        shiroFilterFactoryBean.setSuccessUrl("/common/dashboard");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
//        Map<String,String> map = new LinkedHashMap<String,String>();
//        map.put(" /static/**","anon");
//        map.put("js/**","anon");
//        map.put("/css/**","anon");
//        map.put("/account/login","anon");
//        map.put("/account/doLogin","anon");
//        map.put("/account/register","anon");
//        map.put( "/account/doRegister","anon");
//        map.put("/test/**","anon");
         //如果使用"记住我功能”，则采用user规则，如果必须要用户登录，则采用authc规则
//         map.put("/**","user");
//         map.put("/pay/**","authc");
//         shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
         return shiroFilterFactoryBean;
    }

    /**
     * --注册 shiro 方言，让 thymeleaf 支持 shiro 标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * --自动代理类，支持 Shiro 的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /**
     * --开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
      * --记住我之 rememberMeCookie
      */
    @Bean
    public SimpleCookie rememberMeCookieo(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果 httponly 设置为 true，则客户端不会暴露给客户端脚本代码，
        //使用 Httponly cookie 有助于减少某些类型的跨站点脚本攻击;
        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间,单位是秒
        simpleCookie.setMaxAge(1 *24* 60* 602);
        return simpleCookie;
    }

    /**
      * --记住我之 cookie 管理器
      */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookieo());
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWo18g--");
        cookieRememberMeManager.setCipherService(new AesCipherService());
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
            }

    }
