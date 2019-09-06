package com.example.ModelTest;

import com.example.DTO.SearchByPostCodeResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link SearchByPostCodeResponse}
 */
public class SearchByPostCodeResponseTest {

    private static SearchByPostCodeResponse searchByPostCodeResponse;
    private static String json;

    @BeforeClass
    public static void init() {
        searchByPostCodeResponse = new SearchByPostCodeResponse();
        searchByPostCodeResponse.setCode("value");
        searchByPostCodeResponse.setCity("value");
        searchByPostCodeResponse.setCityKana("value");
        searchByPostCodeResponse.setPrefecture("value");
        searchByPostCodeResponse.setPrefectureKana("value");
        searchByPostCodeResponse.setPrefectureCode("value");
        searchByPostCodeResponse.setArea("value");
        searchByPostCodeResponse.setAreaKana("value");
        searchByPostCodeResponse.setMultiPostArea("value");
        searchByPostCodeResponse.setKoazaArea("value");
        searchByPostCodeResponse.setChomeArea("value");
        searchByPostCodeResponse.setOldPostCode("value");
        searchByPostCodeResponse.setPostCode("value");
        searchByPostCodeResponse.setMultiArea("value");
        searchByPostCodeResponse.setUpdateShow("value");
        searchByPostCodeResponse.setChangeReason("value");
        searchByPostCodeResponse.setPrefectureCode("value");
        json = "{" +
                "'code':'value'," +
                "'city':'value'," +
                "'city_kana':'value'," +
                "'prefecture':'value'," +
                "'prefecture_kana':'value'," +
                "'prefecture_code':'value'," +
                "'area':'value'," +
                "'area_kana':'value'," +
                "'multi_post_area':'value'," +
                "'koaza_area':'value'," +
                "'chome_area':'value'," +
                "'old_post_code':'value'," +
                "'post_code':'value'," +
                "'multi_area':'value'," +
                "'update_show':'value'," +
                "'change_reason':'value'" +
                "}";
    }

    /**
     * Test convert SearchByPostCodeResponse to Json
     */
    @Test
    public void testValueToJson() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(searchByPostCodeResponse);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to SearchByPostCodeResponse
     */
    @Test
    public void testJsonToValue() {
        SearchByPostCodeResponse expected = searchByPostCodeResponse;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        SearchByPostCodeResponse actual = gson.fromJson(json, SearchByPostCodeResponse.class);
        Assert.assertEquals(expected, actual);
    }
}
