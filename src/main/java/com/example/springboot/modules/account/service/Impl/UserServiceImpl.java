package com.example.springboot.modules.account.service.Impl;

import com.example.springboot.modules.account.dao.UserDao;
import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.account.service.UserService;
import com.example.springboot.modules.common.vo.Result;
import com.example.springboot.utils.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Result<User> insertUser(User user) {

        User userTemp = getUserByUserName(user.getUserName());
        if (userTemp != null) {
            return new Result<User>(Result.ResultStatus.FAIL.status, "User name is repeat!");
        }

        user.setCreateDate(new Date());
        //加密
        user.setPassword(MD5Util.getMD5(user.getPassword()));

        userDao.insertUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Insert success", user);
    }

    @Override
    @Transactional
    public Result<User> selectUser(User user) {

        User userTemp = getUserByUserName(user.getUserName());
        if (userTemp != null) {
            return new Result<User>(Result.ResultStatus.SUCCESS.status, "User name is existed");
        } else {
            return new Result<User>(Result.ResultStatus.FAIL.status, "User name is not existed");
        }

    }

    @Override
    public PageInfo<User> getUsersByPage(int currentPage, int pageSize, String userName) {
        //pagehelper 插件使用
        PageHelper.startPage(currentPage, pageSize);
        return new PageInfo<>(Optional.ofNullable(userDao.getUsersByPage(userName))
                .orElse(Collections.emptyList()));
    }

    /*登录*/
    @Override
    public Result<User> login(User user) {

        try{
            Subject subject= SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getUserName(),MD5Util.getMD5(user.getPassword()));
            usernamePasswordToken.setRememberMe(user.getRememberMe());
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        }catch (Exception e){
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAIL.status,"login fail");
        }

        return new Result<User>(Result.ResultStatus.SUCCESS.status,"Login success.",user);
    }

    /*退出登录*/
    @Override
    public void logout() {
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
