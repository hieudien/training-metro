package com.example.Common;

import com.example.ResponeModel.ResponseError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ComponentScan
public class Util {

    /**
     * validate if input is null or is not a number
     *
     * @param input request input
     * @return true if valid
     */
    public static boolean isInputValid(String input) {
        if (input == null) return false;
        return input.chars().allMatch(Character::isDigit);
    }

    /**
     * Convert list object to JSON
     *
     * @param objList list of objects
     * @return JSON format of objects
     */
    public static String convertListObjToJSON(List objList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(objList);
    }

    /**
     * convet a single object to Json
     *
     * @param obj a single object
     * @return JSON format the object
     */
    public static String convertObjToJSON(Object obj) {
        return new Gson().toJson(obj);
    }

    public static ResponseEntity<Object> returnBadRequestStatus() {
        ResponseError responseError = new ResponseError("400", "Post Code is invalid");
        return new ResponseEntity<>(convertObjToJSON(responseError), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> returnNotFoundStatus() {
        ResponseError responseError = new ResponseError("404", "Record not found");
        return new ResponseEntity<>(convertObjToJSON(responseError), HttpStatus.NOT_FOUND);
    }
}
