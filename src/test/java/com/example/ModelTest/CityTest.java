package com.example.ModelTest;

import com.example.Entity.City;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link com.example.Entity.City}
 */
public class CityTest {

    private static City city;
    private static String json;

    @BeforeClass
    public static void init() {
        city = new City();
        city.setCityId("value");
        city.setCode("value");
        city.setCityKana("value");
        city.setCity("value");
        city.setPrefectureId("value");
        json = "{" +
                "'city_id':'value'," +
                "'code':'value'," +
                "'city_kana':'value'," +
                "'city':'value'," +
                "'prefecture_id':'value'" +
                "}";
    }

    /**
     * Test convert City to Json
     */
    @Test
    public void testValueToJson() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(city);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to City
     */
    @Test
    public void testJsonToValue() {
        City expected = city;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        City actual = gson.fromJson(json, City.class);
        Assert.assertEquals(expected, actual);
    }
}
