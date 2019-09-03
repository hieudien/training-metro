package com.example.Repository;

import com.example.Entity.City;
import org.springframework.data.repository.CrudRepository;

/**
 * City Repository
 */
public interface CityRepository extends CrudRepository<City, Long> {

    /**
     * Finde City by cityId
     *
     * @param cityId to find
     * @return a City
     */
    City findByCityId(String cityId);
}
