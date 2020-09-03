package com.example.springboot.modules.test.controller;

import com.example.springboot.modules.test.entity.Country;
import com.example.springboot.modules.test.service.CountryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
public class CountryController {

    private CountryService countryService;

    /*
     * 127.0.0.1:8086/api/country/3
     * */
    @RequestMapping("/country/{countryId}")
    /** @PathVariable 找路径上面对应的参数
     * @RequestParam 将请求参数绑定到你控制器的方法参数上
     */
    public Country getCountryByCountryId(@PathVariable int countryId) {
        return countryService.getCountryByCountryId(countryId);
    }
}
