package com.example.DTOTest;

import com.example.DTO.SearchByPrefectureCodeResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link SearchByPrefectureCodeResponse}
 */
public class SearchByPrefectureCodeResponseTest {

    private static SearchByPrefectureCodeResponse searchByPrefectureCodeResponse;
    private static String json;

    @BeforeClass
    public static void init() {
        searchByPrefectureCodeResponse = new SearchByPrefectureCodeResponse();
        searchByPrefectureCodeResponse.setCode("code");
        searchByPrefectureCodeResponse.setPrefectureCode("code");
        searchByPrefectureCodeResponse.setPrefecture("code");
        searchByPrefectureCodeResponse.setPrefectureKana("code");
        searchByPrefectureCodeResponse.setCity("code");
        searchByPrefectureCodeResponse.setCityKana("code");
        json = "{" +
                "'code':'code'," +
                "'city':'code'," +
                "'city_kana':'code'," +
                "'prefecture':'code'," +
                "'prefecture_kana':'code'," +
                "'prefecture_code':'code'" +
                "}";
    }


    /**
     * Test convert SearchByPrefectureCodeResponse to Json
     */
    @Test
    public void testValueToJson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(searchByPrefectureCodeResponse);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to SearchByPrefectureCodeResponse
     */
    @Test
    public void testJsonToValue() {
        SearchByPrefectureCodeResponse expected = searchByPrefectureCodeResponse;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        SearchByPrefectureCodeResponse actual = gson.fromJson(json, SearchByPrefectureCodeResponse.class);
        Assert.assertEquals(expected, actual);
    }
}
