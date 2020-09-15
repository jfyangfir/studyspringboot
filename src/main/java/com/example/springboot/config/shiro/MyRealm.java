package com.example.springboot.config.shiro;

import com.example.springboot.modules.account.entity.Resource;
import com.example.springboot.modules.account.entity.Role;
import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.account.service.ResourceService;
import com.example.springboot.modules.account.service.RoleService;
import com.example.springboot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    /*资源授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();

        String userName= (String) principalCollection.getPrimaryPrincipal();
        User user=userService.getUserByUserName(userName);
        if(user==null){
            throw new UnknownAccountException("This user is not exist.");
        }

        List<Role> roles=roleService.getRolesByUserId(user.getUserId());
        for(Role role:roles){

            //把查询到的角色信息添加到资源验证器里
            simpleAuthorizationInfo.addRole(role.getRoleName());
            List<Resource> resources=resourceService.getResourcesByRoleId(user.getUserId());
            for(Resource resource:resources){
                simpleAuthorizationInfo.addStringPermission(resource.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }

    /*身份验证*/
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
