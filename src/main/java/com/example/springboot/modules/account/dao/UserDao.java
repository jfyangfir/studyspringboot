package com.example.springboot.modules.account.dao;

import com.example.springboot.modules.account.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Insert("insert into user (create_date,password,user_name)"
             +"values(#{createDate},#{password},#{userName})")
    @Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "userId")
    void insertUser(User user);

    @Select("select * from user where user_name=#{userName}")
    User getUserByUserName(String userName);

}
