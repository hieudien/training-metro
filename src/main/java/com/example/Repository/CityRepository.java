package com.example.Repository;

import com.example.Entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findByCityId(String cityId);
}
