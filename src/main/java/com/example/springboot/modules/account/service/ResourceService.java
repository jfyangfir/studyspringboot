package com.example.springboot.modules.account.service;

import com.example.springboot.modules.account.entity.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> getResourcesByRoleId(int userId);
}
