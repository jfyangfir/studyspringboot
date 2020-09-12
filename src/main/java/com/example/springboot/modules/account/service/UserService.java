package com.example.springboot.modules.account.service;

import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.common.vo.Result;
import com.github.pagehelper.PageInfo;

public interface UserService {

    Result<User> insertUser(User user);

    User getUserByUserName(String userName);

    Result<User> selectUser(User user);

    PageInfo<User> getUsersByPage(int currentPage, int pageSize, String userName);

    Result<User> login(User user);
}
