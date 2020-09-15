package com.example.springboot.modules.account.controller;

import com.example.springboot.modules.account.entity.Role;
import com.example.springboot.modules.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roles/{userId}")
    List<Role> getRolesByUserId(@PathVariable int userId){
        return roleService.getRolesByUserId(userId);
    }
}
