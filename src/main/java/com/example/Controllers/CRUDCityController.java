package com.example.Controllers;

import com.example.Common.Util;
import com.example.Entity.City;
import com.example.Service.CRUDCityService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * CRUD City Controller
 */
@RestController
@RequestMapping(value = "/city")
public class CRUDCityController {

    @Autowired
    private CRUDCityService crudCityService;

    /**
     * Save new City
     *
     * @param city city info from request
     * @return saved city info with HttpStatus
     */
    @PostMapping(path = {"/save"})
    public ResponseEntity<Object> saveCity(@RequestBody City city) {
        if (city == null) return Util.returnBadRequestStatus();
        City savedCity = crudCityService.saveCity(city);
        if (savedCity == null) return Util.returnServerError();
        String result = Util.convertObjToJSON(savedCity);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * find City By CityId
     *
     * @param id get from Url path
     * @return ResponseEntity with data and status
     */
    @GetMapping(path = {"/find", "/find/{id}"})
    public ResponseEntity<Object> findCityByCityId(@PathVariable(name = "id", required = false) String id) {
        if (Util.isInputValid(id) == false) return Util.returnBadRequestStatus();
        City city = crudCityService.findByCityId(id);
        if (city == null) return Util.returnNotFoundStatus();
        String result = Util.convertObjToJSON(city);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update a City
     *
     * @param city get from request
     * @return updated City with OK Status
     */
    @PostMapping(path = {"/update"})
    public ResponseEntity<Object> updateCity(@RequestBody City city) {
        if (city == null || Util.isInputValid(city.getCityId()) == false) return Util.returnBadRequestStatus();
        City updatedCity = crudCityService.updateCity(city);
        if (updatedCity == null) return Util.returnServerError();
        String result = Util.convertObjToJSON(updatedCity);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete a City
     *
     * @param id to delete
     * @return ResponseEntity with OK Status
     */
    @GetMapping(path = {"/delete", "/delete/{id}"})
    public ResponseEntity<Object> deleteCity(@PathVariable(name = "id", required = false) String id) {
        if (Util.isInputValid(id) == false) return Util.returnBadRequestStatus();
        crudCityService.deleteCity(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
