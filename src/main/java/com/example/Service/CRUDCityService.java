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
     * @throws Exception happened when save City
     */
    public City saveCity(City city) throws Exception {
        return cityRepository.save(city);
    }

    /**
     * Find City by City Id
     *
     * @param cityId get from URL
     * @return city
     */
    public City findByCityId(String cityId) {
        City city = cityRepository.findByCityId(cityId);
//        List<City> cityList = (List<City>) cityRepository.findAll();
        System.out.printf(city.toString());
        return city;
    }

    /**
     * Update a City
     *
     * @param city info get from request
     * @return updated City
     * @throws Exception happened when update City
     */
    public City updateCity(City city) throws Exception {
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
    public void deleteCity(String cityId) throws Exception {
        cityRepository.delete(Long.valueOf(cityId));
    }
}
