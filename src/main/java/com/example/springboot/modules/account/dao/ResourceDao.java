package com.example.springboot.modules.account.dao;

import com.example.springboot.modules.account.entity.Resource;
import com.example.springboot.modules.account.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResourceDao {

    @Select("select r.* from resource r " +
            "left join resource_dept d on r.resource_id = d.resource_id " +
            "left join role_dept e on d.role_id = e.role_id " +
            "where e.user_id=#{userId}")
    List<Resource> getResourcesByRoleId(int userId);
}
