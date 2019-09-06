package com.example.ModelTest;

import com.example.Entity.OldPost;
import com.example.ResponeModel.ResponseError;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link com.example.ResponeModel.ResponseError}
 */
public class ResponseErrorTest {

    private static ResponseError responseError;
    private static String json;

    @BeforeClass
    public static void init() {
        responseError = new ResponseError();
        responseError.setError("value");
        responseError.setErrorDesciption("value");
        json = "{" +
                "'error':'value'," +
                "'error_desciption':'value'" +
                "}";
    }

    /**
     * Test convert ResponseError to Json
     */
    @Test
    public void testValueToJson() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(responseError);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to ResponseError
     */
    @Test
    public void testJsonToValue() {
        ResponseError expected = responseError;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ResponseError actual = gson.fromJson(json, ResponseError.class);
        Assert.assertEquals(expected, actual);
    }
}
