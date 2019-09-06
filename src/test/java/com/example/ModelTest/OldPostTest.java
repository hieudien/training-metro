package com.example.ModelTest;

import com.example.Entity.OldPost;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link com.example.Entity.OldPost}
 */
public class OldPostTest {

    private static OldPost oldPost;
    private static String json;

    @BeforeClass
    public static void init() {
        oldPost = new OldPost();
        oldPost.setOldPostId("value");
        oldPost.setOldPostCode("value");
        json = "{" +
                "'old_post_id':'value'," +
                "'old_post_code':'value'" +
                "}";
    }

    /**
     * Test convert OldPost to Json
     */
    @Test
    public void testValueToJson() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String actual = gson.toJson(oldPost);
        String expected = json;
        Assert.assertEquals(actual, expected.replaceAll("'", "\""));
    }

    /**
     * Test convert Json to OldPost
     */
    @Test
    public void testJsonToValue() {
        OldPost expected = oldPost;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        OldPost actual = gson.fromJson(json, OldPost.class);
        Assert.assertEquals(expected, actual);
    }
}
