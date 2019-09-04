package com.example.ServiceTest;

import com.example.Entity.City;
import com.example.Repository.CityRepository;
import com.example.Service.CRUDCityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link CRUDCityService}
 */

@RunWith(MockitoJUnitRunner.class)
public class CRUDCityServiceTest {

    @InjectMocks
    CRUDCityService crudCityService;
    @Mock
    CityRepository cityRepository;

    /**
     * Test save City success
     * Input:
     * A City that not exist in DB
     * Output:
     * Saved City what same with input
     */
    @Test
    public void saveCityTest1() {
        // setup
        City city = new City();
        city.setCode("02116");
        city.setCityKana("ｻｯﾎﾟﾛｼﾁｭｳxｵｳ93");
        city.setCity("札幌市中央区");
        city.setPrefectureId("01");
        when(cityRepository.save(city)).thenReturn(city);
        // exercise
        City savedCity = crudCityService.saveCity(city);
        // verify
        Assert.assertNotNull(savedCity);
        Assert.assertEquals(city.getCode(), savedCity.getCode());
        Assert.assertEquals(city.getCityKana(), savedCity.getCityKana());
        Assert.assertEquals(city.getCity(), savedCity.getCity());
        Assert.assertEquals(city.getPrefectureId(), savedCity.getPrefectureId());

    }

    /**
     * Test save City fail, input city is null
     * Input:
     * null
     * Output:
     * null
     */
    @Test
    public void saveCityTest2() {
        // setup
        City city = null;
        when(cityRepository.save(city)).thenReturn(city);
        // exercise
        City savedCity = crudCityService.saveCity(city);
        // verify
        Assert.assertNull(savedCity);
    }

    /**
     * Test find City success
     * Input:
     * cityId that exist in DB
     * Output:
     * City mapping with input cityId
     */
    @Test
    public void findByCityIdTest1() {
        // setup
        String cityId = "2059";
        City city = new City();
        city.setCityId(cityId);
        city.setCode("05506");
        city.setCityKana("ｻｯﾎﾟﾛｼﾁｭｳxｵ223ｸ");
        city.setCity("ｻｯﾎﾟﾛｼﾁｭｳxｵ223ｸ");
        city.setPrefectureId("9");
        when(cityRepository.findByCityId(cityId)).thenReturn(city);
        // exercise
        City foundCity = crudCityService.findByCityId(cityId);
        // verify
        Assert.assertNotNull(foundCity);
        Assert.assertEquals(city.getCityId(), foundCity.getCityId());
        Assert.assertEquals(city.getCode(), foundCity.getCode());
        Assert.assertEquals(city.getCityKana(), foundCity.getCityKana());
        Assert.assertEquals(city.getCity(), foundCity.getCity());
        Assert.assertEquals(city.getPrefectureId(), foundCity.getPrefectureId());
    }

    /**
     * Test find City when no data mapping with cityId
     * Input:
     * cityId that not exist in DB
     * Output:
     * null
     */
    @Test
    public void findByCityIdTest2() {
        // setup
        String cityId = "9999";
        when(cityRepository.findByCityId(cityId)).thenReturn(null);
        // exercise
        City foundCity = crudCityService.findByCityId(cityId);
        // verify
        Assert.assertNull(foundCity);
    }

    /**
     * Test find City when input cityId is null
     * Input:
     * cityId that not exist in DB
     * Output:
     * null
     */
    @Test
    public void findByCityIdTest3() {
        // setup
        when(cityRepository.findByCityId(null)).thenReturn(null);
        // exercise
        City foundCity = crudCityService.findByCityId(null);
        // verify
        Assert.assertNull(foundCity);
    }

    /**
     * Test update City success
     * Input:
     * A City has cityId that exist in DB, with some different info
     * Output:
     * Updated City with new info
     */
    @Test
    public void updateCityTest1() {
        // setup
        String cityId = "2059";
        City city = new City();
        city.setCityId(cityId);
        city.setCode("05906");
        city.setCityKana("new Kana");
        city.setCity("new ｻｯﾎﾟﾛｼﾁｭｳxｵ");
        city.setPrefectureId("10");
        when(cityRepository.findByCityId(cityId)).thenReturn(city);
        when(cityRepository.save(city)).thenReturn(city);
        // exercise
        City updatedCity = crudCityService.updateCity(city);
        // verify
        Assert.assertNotNull(updatedCity);
        Assert.assertEquals(city.getCityId(), updatedCity.getCityId());
        Assert.assertEquals(city.getCode(), updatedCity.getCode());
        Assert.assertEquals(city.getCityKana(), updatedCity.getCityKana());
        Assert.assertEquals(city.getCity(), updatedCity.getCity());
        Assert.assertEquals(city.getPrefectureId(), updatedCity.getPrefectureId());
    }

    /**
     * Test update City failed, input city is null
     * Input:
     * null
     * Output:
     * null
     */
    @Test
    public void updateCityTest2() {
        // setup
        City city = null;
        when(cityRepository.findByCityId(anyString())).thenReturn(city);
        when(cityRepository.save(city)).thenReturn(city);
        // exercise
        City updatedCity = crudCityService.updateCity(city);
        // verify
        Assert.assertNull(updatedCity);
    }

    /**
     * function delete trả về void nên chưa biết test ntn
     */
    @Test
    public void deleteCityTest() {
        String cityId = "200";
        crudCityService.deleteCity(cityId);
    }
}
