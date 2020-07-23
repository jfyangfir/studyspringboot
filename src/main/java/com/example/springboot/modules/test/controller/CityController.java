package com.example.springboot.modules.test.controller;

import com.example.springboot.modules.test.entity.City;
import com.example.springboot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    /*
    * 127.0.0.1/api/cities/1
    * */
    @RequestMapping("/cities/{id}")
//    @PathVariable找路径上面对应的参数  @RequestParam
    public List<City> getCitiesById(@PathVariable int id){
          return cityService.getCitiesById(id);
    }
}
