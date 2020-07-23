package com.example.springboot.modules.test.service;

import com.example.springboot.modules.test.entity.City;

import java.util.List;

public interface CityService {

    List<City> getCitiesById(int id);
}
