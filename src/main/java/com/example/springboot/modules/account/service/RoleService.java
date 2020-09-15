package com.example.springboot.modules.account.service;

import com.example.springboot.modules.account.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByUserId(int userId);

}
