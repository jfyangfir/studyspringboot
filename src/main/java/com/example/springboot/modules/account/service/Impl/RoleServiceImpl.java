package com.example.springboot.modules.account.service.Impl;

import com.example.springboot.modules.account.dao.RoleDao;
import com.example.springboot.modules.account.dao.UserDao;
import com.example.springboot.modules.account.entity.Role;
import com.example.springboot.modules.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRolesByUserId(int userId) {
        return roleDao.getRolesByUserId(userId);
    }
}
