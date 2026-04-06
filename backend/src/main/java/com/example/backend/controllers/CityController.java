package com.example.backend.controllers;

import com.example.backend.entities.City;
import com.example.backend.services.CityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityServiceImpl cityServiceImpl;

    public CityController(CityServiceImpl cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    @PostMapping("/add/add")
    public ResponseEntity<City> saveCity(@RequestBody City city){
        return new ResponseEntity<>(this.cityServiceImpl.saveCity(city), HttpStatus.CREATED);
    }
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities(){
        return new ResponseEntity<>(this.cityServiceImpl.getAllCities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        return new ResponseEntity<>(this.cityServiceImpl.getCityById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable Long id){
         this.cityServiceImpl.deleteCity(id);
         return  ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city){

        return new ResponseEntity<>(this.cityServiceImpl.updateCity(id, city), HttpStatus.OK);

    }

    @GetMapping("/city/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable String name){
        return new ResponseEntity<>(this.cityServiceImpl.getCityByName(name),HttpStatus.OK);
    }
}
