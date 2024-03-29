package com.example.ControllerTest;

import com.example.Controllers.CRUDCityController;
import com.example.Entity.City;
import com.example.Service.CRUDCityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test for {@link com.example.Controllers.CRUDCityController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CRUDCityControllerTest {
    @Mock
    CRUDCityService crudCityService;
    @InjectMocks
    CRUDCityController sut;
    private MockMvc mockMvc;

    private static final String CITY_PATH = "/city";
    private static final String SAVE = "/save";
    private static final String FIND = "//find/{id}";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete/{id}";

    /**
     * Setup
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    /**
     * Test save a city success
     * Input:
     * A city that not exists in DB
     * Output:
     * Response with OK status
     *
     * @throws Exception exception exception
     */
    @Test
    public void saveCityTest1() throws Exception {
        // setup
        City city = new City();
        city.setCityId("011");
        city.setCity("city");
        city.setCode("code");
        city.setCityKana("cityKana");
        city.setPrefectureId("prefectureId");
        Mockito.when(crudCityService.saveCity(city)).thenReturn(city);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(city);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(CITY_PATH + SAVE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test save a city fail,
     * Input:
     * Request city is null
     * Output:
     * Response with Bad request Status
     *
     * @throws Exception exception exception
     */
    @Test
    public void saveCityTest2() throws Exception {
        // setup
        City city = null;
        Mockito.when(crudCityService.saveCity(city)).thenReturn(city);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(city);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(CITY_PATH + SAVE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test save a city, duplicate key error
     * Input:
     * A city that exists in DB
     * Output:
     * Response with Server Error Status
     *
     * @throws Exception exception exception
     */
    @Test
    public void saveCityTest3() throws Exception {
        // setup
        City city = new City();
        city.setCityId("011");
        city.setCity("city");
        city.setCode("code");
        city.setCityKana("cityKana");
        city.setPrefectureId("prefectureId");
        Mockito.when(crudCityService.saveCity(city)).thenReturn(null);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(city);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(CITY_PATH + SAVE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    /**
     * Test find city by cityId success
     * Input:
     * CityId that exists in DB
     * Output:
     * Response with OK status
     *
     * @throws Exception exception
     */
    @Test
    public void findCityByCityIdTest1() throws Exception {
        // setup
        String cityId = "1234";
        Mockito.when(crudCityService.findByCityId(cityId)).thenReturn(new City());
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + FIND, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test find city by cityId success, input cityId is null
     * Input:
     * null
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void findCityByCityIdTest2() throws Exception {
        // setup
        String cityId = null;
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + FIND, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test find city by cityId success, input cityId is not a number
     * Input:
     * input cityId is not a number
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void findCityByCityIdTest3() throws Exception {
        // setup
        String cityId = "cityId";
        Mockito.when(crudCityService.findByCityId(cityId)).thenReturn(new City());
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + FIND, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test find city by cityId, there is no record be found
     * Input:
     * CityId that exists in DB
     * Output:
     * Response with Not Found Status
     *
     * @throws Exception exception
     */
    @Test
    public void findCityByCityIdTest4() throws Exception {
        // setup
        String cityId = "1234";
        Mockito.when(crudCityService.findByCityId(cityId)).thenReturn(null);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + FIND, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test update a city success
     * Input:
     * A city that exists in DB with some different information
     * Output:
     * Response with updated city and OK status
     *
     * @throws Exception exception
     */
    @Test
    public void updateCityTest1() throws Exception {
        // setup
        City city = new City();
        city.setCityId("011");
        city.setCity("city");
        city.setCode("code");
        city.setCityKana("cityKana");
        city.setPrefectureId("prefectureId");
        Mockito.when(crudCityService.updateCity(city)).thenReturn(city);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(city);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(CITY_PATH + UPDATE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test update a city fail, input city is null
     * Input:
     * null
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void updateCityTest2() throws Exception {
        // setup
        City city = null;
        Mockito.when(crudCityService.updateCity(city)).thenReturn(city);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(city);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(CITY_PATH + UPDATE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test update a city fail, server error
     * Input:
     * A city that exists in DB with some different information
     * Output:
     * Response with Server Error Status
     *
     * @throws Exception exception
     */
    @Test
    public void updateCityTest3() throws Exception {
        // setup
        City city = new City();
        city.setCityId("011");
        city.setCity("city");
        city.setCode("code");
        city.setCityKana("cityKana");
        city.setPrefectureId("prefectureId");
        Mockito.when(crudCityService.updateCity(city)).thenReturn(null);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(city);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(CITY_PATH + UPDATE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    /**
     * Test delete a city success
     * Input:
     * CityId that exists in DB
     * Output:
     * Response with OK status
     *
     * @throws Exception exception
     */
    @Test
    public void deleteCityTest1() throws Exception {
        // setup
        String cityId = "1234";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + DELETE, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test delete a city fail, input cityId is null
     * Input:
     * null
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void deleteCityTest2() throws Exception {
        // setup
        String cityId = null;
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + DELETE, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test delete a city fail, input cityId is not a number
     * Input:
     * input cityId is not a number
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void deleteCityTest3() throws Exception {
        // setup
        String cityId = "cityID";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(CITY_PATH + DELETE, cityId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
