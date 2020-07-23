package com.example.springboot.modules.test.dao;

import com.example.springboot.modules.test.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CityDao {


    List<City> getCitiesById(int id);

    @Select("select * from city where id=#{id}")
    List<City> getCitiesById2(int id);
}
