package com.example.springboot.modules.account.service.Impl;

import com.example.springboot.modules.account.dao.UserDao;
import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.account.service.UserService;
import com.example.springboot.modules.common.vo.Result;
import com.example.springboot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Result<User> insertUser(User user) {

        User userTemp=getUserByUserName(user.getUserName());
        if(userTemp!=null){
            return new Result<User>(Result.ResultStatus.FAIL.status,"User name is repeat!");
        }

        user.setCreateDate(new Date());
        user.setPassword(MD5Util.getMD5(user.getPassword()));

        userDao.insertUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"Insert success",user);
    }

    @Override
    @Transactional
    public Result<User> selectUser(User user) {

        User userTemp=getUserByUserName(user.getUserName());
        if(userTemp!=null){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"User name is existed");
        }else {
            return new Result<User>(Result.ResultStatus.FAIL.status,"User name is not existed");
        }

    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
