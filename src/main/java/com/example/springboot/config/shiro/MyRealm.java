package com.example.springboot.config.shiro;

import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        User user=userService.getUserByUserName(userName);
        if(user==null){
            throw new UnknownAccountException("This user name do not exist.");
        }
        //身份验证器，包装用户名和密码
        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),getName());
    }

}
