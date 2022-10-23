package com.pavelchak.controller.impl;

import com.pavelchak.controller.CityController;
import com.pavelchak.domain.City;
import com.pavelchak.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityControllerImpl implements CityController {
    @Autowired
    CityService cityService;

    @Override
    public List<City> findAll() {
        return cityService.findAll();
    }

    @Override
    public Optional<City> findById(String cityName) {
        return cityService.findById(cityName);
    }

    @Override
    public int create(City city) {
        return cityService.create(city);
    }

    @Override
    public int update(String cityName, City city) {
        return cityService.update(cityName, city);
    }

    @Override
    public int delete(String cityName) {
        return cityService.delete(cityName);
    }
}
