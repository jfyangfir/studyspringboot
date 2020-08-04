package com.example.springboot.modules.test.dao;

import com.example.springboot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityDao {

        /**
        * @Results     封装结果集，对于联表查询的字段，可调用已有的方法 getCountryByCountryId()
        * column       对应 select 查询后的某个字段名，作为映射实体 bean 属性或者作为调用方法的参数
        * property    对应实体 bean 属性
        * 1. country_id 封装了两次，分别对应 countryId 和 countries,
        *    而 countries 属性通过 getCountryByCountryId() 方法来实现, CountryId 作为参数
        * 2.结果集共享，设置 id 属性，调用时使用 @ResultMap(value="countryResult")
        */

        //组合查询（联表查询）
        @Select("select * from city where id=#{id}")
        @Results (id = "cityResult",value = {
                  @Result(column = "countryId",property = "countryId"),
                  @Result(column = "countryId",property = "countries",
                          javaType = List.class,
                          many = @Many(select = "com.example.springboot.modules.test.dao.CountryDao.getCountryByCountryId"))}
                  )
        List<City> getCitiesById(int id);

        @Select("select * from city where cityName=#{cityName}")
        @ResultMap(value = {"cityResult"})
        City getCityByCityName(String cityName);

        @Insert("insert into city (cityName,person,countryId,dateCreated)"
        + "values(#{cityName},#{person},#{countryId},#{dateCreated})")
//        绑定id作一对一的city映射，若未绑定city对象中id则为null
        @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
        void insertCity(City city);

        @Update("update city set person = #{person} where id = #{id}")
        void updateCity(City city);

        @Delete("delete from city where id=#{id}")
        void deleteCity(int id);

        @Select("select * from city where countryId=#{countryId}")
        List<City> getCitiesByCountryId(int countryId);

}
