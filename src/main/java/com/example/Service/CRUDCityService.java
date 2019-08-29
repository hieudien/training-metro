package com.example.Service;

import com.example.Entity.City;
import com.example.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CRUDCityService {

    @Autowired
    private CityRepository cityRepository;

    /**
     * Find City by City Id
     *
     * @param cityId get from URL
     * @return list cities
     */
    public List<City> findByCityId(String cityId) {
        List<City> cityList = cityRepository.findByCityId(cityId);
//        List<City> cityList = (List<City>) cityRepository.findAll();
        cityList.forEach(city -> System.out.printf(city.getCityKana()));
        return cityList;
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }
}
