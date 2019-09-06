package com.example.ModelTest;

import com.example.DTO.SearchByPostCodeRequest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link com.example.DTO.SearchByPostCodeRequest}
 */
public class SearchByPostCodeRequestTest {

    private static SearchByPostCodeRequest searchByPostCodeRequest;
    private static String json;

    @BeforeClass
    public static void init() {
        searchByPostCodeRequest = new SearchByPostCodeRequest();
        searchByPostCodeRequest.setPostCode("value");
        json = "{" +
                "'post_code':'value'" +
                "}";
    }

    /**
     * Test convert SearchByPostCodeRequestTest to Json
     */
    @Test
    public void testValueToJson() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(searchByPostCodeRequest);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to SearchByPostCodeRequestTest
     */
    @Test
    public void testJsonToValue() {
        SearchByPostCodeRequest expected = searchByPostCodeRequest;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        SearchByPostCodeRequest actual = gson.fromJson(json, SearchByPostCodeRequest.class);
        Assert.assertEquals(expected, actual);
    }
}
