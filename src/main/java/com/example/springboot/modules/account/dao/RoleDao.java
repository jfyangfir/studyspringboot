package com.example.springboot.modules.account.dao;

import com.example.springboot.modules.account.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select r.* from role r " +
            "left join role_dept d on r.role_id = d.role_id " +
            "where d.user_id=#{userId}")
    List<Role> getRolesByUserId(int userId);
}
