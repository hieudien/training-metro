package com.example.DTOTest;

import com.example.DTO.SearchByPrefectureCodeResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.json.ObjectContent;

public class SearchByPrefectureCodeResponseTest {
    /**
     * Test convert SearchByPrefectureCodeResponse to Json
     */
    @Test
    public void testValueToJson() {
        SearchByPrefectureCodeResponse searchByPrefectureCodeResponse = new SearchByPrefectureCodeResponse();
        searchByPrefectureCodeResponse.setCode("code");
        searchByPrefectureCodeResponse.setPrefectureCode("code");
        searchByPrefectureCodeResponse.setPrefecture("code");
        searchByPrefectureCodeResponse.setPrefectureKana("code");
        searchByPrefectureCodeResponse.setCity("code");
        searchByPrefectureCodeResponse.setCityKana("code");
        String actual = new Gson().toJson(searchByPrefectureCodeResponse);
        String expected = "{" +
                "'code':'code'," +
                "'city':'code'," +
                "'cityKana':'code'," +
                "'prefecture':'code'," +
                "'prefectureKana':'code'," +
                "'prefectureCode':'code'" +
                "}";
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to SearchByPrefectureCodeResponse
     */
    @Test
    public void testJsonToValue() {
        SearchByPrefectureCodeResponse expected = new SearchByPrefectureCodeResponse();
        expected.setCode("code");
        expected.setPrefectureCode("code");
        expected.setPrefecture("code");
        expected.setPrefectureKana("code");
        expected.setCity("code");
        expected.setCityKana("code");
        String json = "{" +
                "'code':'code'," +
                "'city':'code'," +
                "'cityKana':'code'," +
                "'prefecture':'code'," +
                "'prefectureKana':'code'," +
                "'prefectureCode':'code'" +
                "}";
        SearchByPrefectureCodeResponse actual = new Gson().fromJson(json, SearchByPrefectureCodeResponse.class);
        Assert.assertThat(actual).;
    }
}
