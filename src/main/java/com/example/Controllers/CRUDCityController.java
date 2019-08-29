package com.example.Controllers;

import com.example.Common.Util;
import com.example.Entity.City;
import com.example.Service.CRUDCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * find City By City Id
     * @param id get from Url path
     * @return ResponseEntity with data and status
     */
    @GetMapping(path = {"/find", "/find/{id}"})
    public ResponseEntity<Object> findCityByCityId(@PathVariable(name = "id", required = false) String id) {
        if (Util.isInputValid(id) == false) return Util.returnBadRequestStatus();
        List<City> cityList = crudCityService.findByCityId(id);
        if (cityList.size() == 0) return Util.returnNotFoundStatus();
        String result = Util.convertListObjToJSON(cityList);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
