package com.example.backend.services;

import com.example.backend.entities.City;

import java.util.List;

public interface ICityService {


    List<City> getAllCities();
    City getCityById(Long id);
    City saveCity(City city);
    City updateCity(Long id, City city);
    void deleteCity(Long id);
    City getCityByName(String name);
}
