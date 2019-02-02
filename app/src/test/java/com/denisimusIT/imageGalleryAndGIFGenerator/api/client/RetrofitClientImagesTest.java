package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import org.junit.Ignore;
import org.junit.Test;


import java.io.File;
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

    @Ignore
    @Test
    public void addImageTestErrorIdntLogin() throws IOException {
        //TODO
        RetrofitClient retrofitClient = new RetrofitClient();
        File image = null;
        String description = "";
        String hashtag = "";
        int latitude = 0;
        int longitude = 0;

        String expected = "{\"error\":\"Invalid access token\"}";

        String actual = retrofitClient.serverApi.addImage(image, description, hashtag, latitude, longitude).toString();
        assertEquals("addImageTestErrorIdntLogin",expected, actual);
    }

}