package com.example.springboot.modules.account.service;

import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.common.vo.Result;

public interface UserService {

    Result<User> insertUser(User user);

    User getUserByUserName(String userName);

    Result<User> selectUser(User user);
}
