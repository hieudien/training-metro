package com.example.Service;

import com.example.Entity.City;
import com.example.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CRUDCityService {

    @Autowired
    private CityRepository cityRepository;

    /**
     * Save a City to DB
     *
     * @param city get from request
     * @return saved City info
     */
    public City saveCity(City city) {
        return (city == null) ? null : cityRepository.save(city);
    }

    /**
     * Find City by City Id
     *
     * @param cityId get from URL
     * @return city
     */
    public City findByCityId(String cityId) {
        return (cityId == null) ? null : cityRepository.findByCityId(cityId);
    }

    /**
     * Update a City
     *
     * @param city info get from request
     * @return updated City
     */
    public City updateCity(City city) {
        if (city == null || city.getCityId() == null) return null;
        City updateCity = cityRepository.findByCityId(city.getCityId());
        updateCity.setCode(city.getCode());
        updateCity.setCityKana(city.getCityKana());
        updateCity.setCity(city.getCity());
        updateCity.setPrefectureId(city.getPrefectureId());
        return cityRepository.save(updateCity);
    }

    /**
     * Delete a City
     *
     * @param cityId to delete
     */
    public void deleteCity(String cityId) {
        cityRepository.delete(cityId);
    }
}
