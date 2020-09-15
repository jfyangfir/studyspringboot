package com.example.springboot.modules.account.service.Impl;

import com.example.springboot.modules.account.dao.ResourceDao;
import com.example.springboot.modules.account.entity.Resource;
import com.example.springboot.modules.account.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Resource> getResourcesByRoleId(int userId) {
        return resourceDao.getResourcesByRoleId(userId);
    }
}
