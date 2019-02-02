package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RetrofitClientImagesAuthorizationTest {

    @Test
    public void CrateNewUserErrorEmptyFieldsTest() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();

        String username = "";
        String email = "";
        String password = "";
        File avatar = null;

        String expected = "{\"children\":{\"username\":{}," +
                "\"email\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"password\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"avatar\":{\"errors\":[\"Please, upload the profile avatar picture.\"]}}}";
        String actual = retrofitClient.serverApi.createNewUser(username, email, password, avatar).execute().errorBody().string();
        assertEquals("CrateNewUserErrorEmptyFields", expected, actual);
    }





}