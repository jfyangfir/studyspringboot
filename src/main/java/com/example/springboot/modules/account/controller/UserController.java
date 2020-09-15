package com.example.springboot.modules.account.controller;

import com.example.springboot.modules.account.entity.User;
import com.example.springboot.modules.account.service.UserService;
import com.example.springboot.modules.common.vo.Result;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user", consumes = "application/json")
    @RequiresPermissions(value = {"/api/user"},logical = Logical.OR)
    public Result<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public Result<User> selectUser(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping(value = "/getUsers", consumes = "application/json")
    @RequiresPermissions(value = {"/api/getUsers"},logical = Logical.OR)
    public PageInfo<User> getUsersByPage(@RequestParam int currentPage, @RequestParam int pageSize, @RequestParam String userName) {
        return userService.getUsersByPage(currentPage, pageSize, userName);
    }
}
