package com.example.springboot.modules.account.controller;

import com.example.springboot.modules.account.entity.Resource;
import com.example.springboot.modules.account.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/resources/{userId}")
    List<Resource> getResourcesByRoleId(@PathVariable int userId){
        return resourceService.getResourcesByRoleId(userId);
    }
}
