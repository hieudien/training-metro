package com.example.IT;

import com.example.Entity.City;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;
    private static Gson gson;
    private static final String CITY_PATH = "/city";
    private static final String SAVE = "/save";
    private static final String FIND = "//find/{id}";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete/{id}";

    @BeforeClass
    public static void setup() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    /**
     * Test saveCity success
     * Input:
     * Request with city information
     * Output:
     * Response with saved city
     */
    @Test
    public void saveCityTest1() {
        // setup
        HttpHeaders headers = new HttpHeaders();
        City requestCity = new City();
        requestCity.setCityId("2059");
        requestCity.setCode("05506");
        requestCity.setCityKana("ｻｯﾎﾟﾛｼﾁｭｳxｵ223ｸ");
        requestCity.setCity("ｻｯﾎﾟﾛｼﾁｭｳxｵ223ｸ");
        requestCity.setPrefectureId("9");
        // exercise
        ResponseEntity<String> actual =
                restTemplate.exchange(CITY_PATH + SAVE, HttpMethod.POST, new HttpEntity<>(requestCity, headers), String.class);
        // verify
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(gson.toJson(requestCity));
    }

    /**
     * Test saveCity fail, request is null
     * Input:
     * Request is null
     * Output:
     * Response with saved city and OK status
     */
    @Test
    public void saveCityTest2() {
        // setup
        HttpHeaders headers = new HttpHeaders();
        City requestCity = null;
        // exercise
        ResponseEntity<String> actual =
                restTemplate.exchange(CITY_PATH + SAVE, HttpMethod.POST, new HttpEntity<>(requestCity, headers), String.class);
        // verify
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(actual.getBody()).isEqualTo(gson.toJson(requestCity));
    }
}
