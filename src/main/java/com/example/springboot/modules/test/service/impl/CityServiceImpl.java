package com.example.springboot.modules.test.service.impl;

import com.example.springboot.modules.test.dao.CityDao;
import com.example.springboot.modules.test.entity.City;
import com.example.springboot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesById(int id) {
//        return cityDao.getCitiesById(id);
        return Optional.ofNullable(cityDao.getCitiesById2(id))
                .orElse(Collections.emptyList());
    }
}
