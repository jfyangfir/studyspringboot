package com.example.springboot.modules.account.controller;

import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.account.service.UserService;
import com.example.springboot.modules.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/user",consumes = "application/json")
    public Result<User> insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }
    @PostMapping(value="/login",consumes = "application/json")
    public Result<User> selectUser(@RequestBody User user){
        return userService.selectUser(user);
    }
}
