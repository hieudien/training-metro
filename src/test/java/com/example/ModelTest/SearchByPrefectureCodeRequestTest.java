package com.example.ModelTest;

import com.example.DTO.SearchByPrefectureCodeRequest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link com.example.DTO.SearchByPrefectureCodeRequest}
 */
public class SearchByPrefectureCodeRequestTest {

    private static SearchByPrefectureCodeRequest searchByPrefectureCodeRequest;
    private static String json;

    @BeforeClass
    public static void init() {
        searchByPrefectureCodeRequest = new SearchByPrefectureCodeRequest();
        searchByPrefectureCodeRequest.setPrefectureCode("value");
        json = "{" +
                "'prefecture_code':'value'" +
                "}";
    }

    /**
     * Test convert SearchByPrefectureCodeRequest to Json
     */
    @Test
    public void testValueToJson() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(searchByPrefectureCodeRequest);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to SearchByPrefectureCodeRequest
     */
    @Test
    public void testJsonToValue() {
        SearchByPrefectureCodeRequest expected = searchByPrefectureCodeRequest;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        SearchByPrefectureCodeRequest actual = gson.fromJson(json, SearchByPrefectureCodeRequest.class);
        Assert.assertEquals(expected, actual);
    }
}
