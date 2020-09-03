package com.example.springboot.modules.test.service.impl;

import com.example.springboot.modules.common.vo.Result;
import com.example.springboot.modules.test.dao.CityDao;
import com.example.springboot.modules.test.entity.City;
import com.example.springboot.modules.test.service.CityService;
import com.example.springboot.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CityDao cityDao;

    public List<City> getCitiesById(int id) {
//        return cityDao.getCitiesById(id);
        return Optional.ofNullable(cityDao.getCitiesById(id))
                .orElse(Collections.emptyList());
    }

    public City getCityByCityName(String cityName) {
        return cityDao.getCityByCityName(cityName);
    }

    //pagehelper 插件使用
    public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int id) {
        PageHelper.startPage(currentPage, pageSize);
        return new PageInfo<>(Optional.ofNullable(cityDao.getCitiesById(id))
                .orElse(Collections.emptyList()));
    }

    @Override
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status, "Insert success", city);
    }

    @Override
    /** 传播机制
     * 保证同一个事务中
     * TransactionDefinition.PROPAGATION _REQUIRED ---如果当前存在事务,则加入该事务.
     *   如果当前没有事务,则创建一个新的事务,这是默认值;
     *   TransactionDefinition.SUPPORTS ----支持当前事务,如果不存在,就不使用事务;
     *   TransactionDefinition.MANDATORY ----支持当前事务,如果不存在,抛出异常;
     * 保证没有在同一个事务中
     *   TransactionDefinition.REQUIRES_ NEW ---如果有事务存在,挂起当前事务,创建一个新的事务,新的执行完毕,继续执行老的事务;
     *   TransactionDefinition.NOT_ SUPPORTED ---以非事务方式运行,如果有事务存在,挂起当前事务;
     *   TransactionDefinition.NEVER ---以非事务方式运行,如果有事务存在,抛出异常;
     *   TransactionDefinition.PROPAGATION. NESTED ---如果当前事务存在,则嵌套事务执行;
     */

//    @Transactional(noRollbackFor = ,propagation = )  noRollbackFor 表示遇到某种异常不回滚   propagation 配置传播方式
    @Transactional
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status, "update success", city);
    }

    @Override
    public Result<Object> deleteCity(int id) {
        cityDao.deleteCity(id);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status, "delete success");

    }

    @Override
    public Object migrateCitiesById(int id) {
        List<City> cities = getCitiesById(id);
        redisUtils.set("cities", cities);
        return redisUtils.get("cities");
    }

    public List<City> getCitiesByCountryId(int countryId) {
        return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

}
