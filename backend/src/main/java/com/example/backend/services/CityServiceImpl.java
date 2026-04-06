package com.example.backend.services;

import com.example.backend.entities.City;
import com.example.backend.exceptions.CityNotFoundException;
import com.example.backend.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService{

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City saveCity(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return this.cityRepository.findAll();
    }

    @Override
    public City getCityById(Long id) {
        return this.cityRepository.findById(id).orElseThrow(() ->new CityNotFoundException("Ville non trouvé."));
    }

    @Override
    public City updateCity(Long id, City city) {
        City cityExisted = getCityById(id);
        if(city.getName() != null){
            cityExisted.setName(city.getName());
        }
       if(city.getCountry() != null){
           cityExisted.setCountry(city.getCountry());
       }
       if(city.getDescription() != null){
           cityExisted.setDescription(city.getDescription());
       }
       if(city.getPostalCode() != null){
           cityExisted.setPostalCode(city.getPostalCode());
       }
       return this.cityRepository.save(cityExisted);

    }

    @Override
    public void deleteCity(Long id) {

        City cityToDelete = getCityById(id);
        this.cityRepository.delete(cityToDelete);

    }

    @Override
    public City getCityByName(String name) {
       // City city = this.cityRepository.findByName()
       return  this.cityRepository.findByName(name).orElseThrow(() -> new CityNotFoundException("Ville avec le nom : " + name + " n'existe pas !"));
    }
}
