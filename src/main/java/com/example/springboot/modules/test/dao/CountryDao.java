package com.example.springboot.modules.test.dao;

import com.example.springboot.modules.test.entity.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CountryDao {

    @Select("select * from country where countryId=#{countryId}")
    Country getCountryByCountryId(int countryId);

}
