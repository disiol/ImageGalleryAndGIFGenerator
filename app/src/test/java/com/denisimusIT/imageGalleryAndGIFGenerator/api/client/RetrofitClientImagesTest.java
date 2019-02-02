package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import org.junit.Ignore;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RetrofitClientImagesTest {

    @Test
    public void getAllUserImagesTestErrorIdntLogin() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();

        String expected = "{\"error\":\"Invalid access token\"}";
        String actual = retrofitClient.serverApi.getAllUserImages().execute().errorBody().string();
        assertEquals("getAllUserImagesTestErrorIdntLogin",expected, actual);
    }

    @Ignore(" надо доделать")
    @Test
    public void getAllUserImagesTestWizLogin() throws IOException {
        //TODO
        RetrofitClient retrofitClient = new RetrofitClient();

        String expected = "{\"error\":\"Invalid access token\"}";
        String actual = retrofitClient.serverApi.getAllUserImages().execute().errorBody().string();
        assertEquals("getAllUserImagesTestWizLogin",expected, actual);
    }

    @Test
    public void getGifTestErrorIdntLogin() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();

        String expected = "{\"error\":\"Invalid access token\"}";
        String actual = retrofitClient.serverApi.getGif().execute().errorBody().string();
        assertEquals("getGifTestErrorIdntLogin",expected, actual);
    }

}