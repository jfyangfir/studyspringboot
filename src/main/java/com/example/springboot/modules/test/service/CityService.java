package com.example.springboot.modules.test.service;

import com.example.springboot.modules.common.vo.Result;
import com.example.springboot.modules.test.entity.City;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CityService {

    List<City> getCitiesById(int id);

    City getCityByCityName(String cityName);

    PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int id);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<Object> deleteCity(int id);
}
