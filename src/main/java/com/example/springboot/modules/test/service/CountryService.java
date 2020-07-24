package com.example.springboot.modules.test.service;

import com.example.springboot.modules.test.entity.Country;

public interface CountryService {

    Country getCountryByCountryId(int countryId);

}
