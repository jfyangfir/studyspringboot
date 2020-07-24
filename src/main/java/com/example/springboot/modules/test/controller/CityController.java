package com.example.springboot.modules.test.controller;

import com.example.springboot.modules.test.entity.City;
import com.example.springboot.modules.test.service.CityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    /*
    * 127.0.0.1:8086/api/cities/1
    * */
    @RequestMapping("/cities/{id}")
//    @PathVariable找路径上面对应的参数
    public List<City> getCitiesById(@PathVariable int id){
          return cityService.getCitiesById(id);
    }

    /*
     * 127.0.0.1:8086/api/city?cityName=
     * */
    @RequestMapping("/city")
//    @PathVariable 找路径上面对应的参数
//    @RequestParam 将请求参数绑定到你控制器的方法参数上
    public City getCityByCityName(@RequestParam String cityName){
          return cityService.getCityByCityName(cityName);
    }

    /*
     * 127.0.0.1:8086/api/cities?currentPage=1&pageSize=4&id=1
     * */
    @RequestMapping("/cities")
    public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, @RequestParam int pageSize,@RequestParam int id){
        return cityService.getCitiesByPage(currentPage,pageSize,id);
    }
}
