package com.example.Controllers;

import com.example.Common.Util;
import com.example.Entity.City;
import com.example.ResponeModel.ResponseError;
import com.example.DTO.SearchByPostCodeRequest;
import com.example.DTO.SearchByPostCodeResponse;
import com.example.DTO.SearchByPrefectureCodeRequest;
import com.example.DTO.SearchByPrefectureCodeResponse;
import com.example.Service.SearchAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Search Address Controller
 */
@RestController
@RequestMapping(value = "/post_offices")
public class SearchAddressController {

    @Autowired
    private SearchAddressService searchAddressService;

    /**
     * Search By PostCode
     *
     * @param postCode get from URL path
     * @return ResponseEntity with data and status
     */
    @GetMapping(path = {"/post", "/post/{postCode}"})
    public ResponseEntity<Object> searchByPostCode(@PathVariable(name = "postCode", required = false) String postCode) {
        // validate input, if input is invalid, return 400 error
        if (Util.isInputValid(postCode) == false) return Util.returnBadRequestStatus();
        SearchByPostCodeRequest searchByPostCodeRequest = new SearchByPostCodeRequest();
        searchByPostCodeRequest.setPostCode(postCode);
        List<SearchByPostCodeResponse> resultList = searchAddressService.searchByPostCode(searchByPostCodeRequest);

        // if record number = 0, return 404 error
        if (resultList.size() == 0) return Util.returnNotFoundStatus();

        String result = Util.convertListObjToJSON(resultList);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Search By PrefectureCode
     *
     * @param prefectureCode get from Url path
     * @return ResponseEntity with data and status
     */
    @GetMapping(path = {"/prefectures", "/prefectures/{prefectureCode}"})
    public ResponseEntity<Object> searchByPrefectureCode(@PathVariable(name = "prefectureCode", required = false) String prefectureCode) {
        // validate input, if input is invalid, return 400 error
        if (Util.isInputValid(prefectureCode) == false) return Util.returnBadRequestStatus();
        SearchByPrefectureCodeRequest searchByPrefectureCodeRequest = new SearchByPrefectureCodeRequest();
        searchByPrefectureCodeRequest.setPrefectureCode(prefectureCode);
        List<SearchByPrefectureCodeResponse> resultList = searchAddressService.searchByPrefectureCode(searchByPrefectureCodeRequest);

        // if record number = 0, return 404 error
        if (resultList.size() == 0) return Util.returnNotFoundStatus();

        String result = Util.convertListObjToJSON(resultList);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
