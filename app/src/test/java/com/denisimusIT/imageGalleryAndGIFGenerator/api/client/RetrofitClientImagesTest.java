package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages.PucturesListParser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RetrofitClientImagesTest {
    PucturesListParser pucturesListParser = new PucturesListParser();

    @Test
    public void getAllUserImagesTestErrorIdntLogin() throws IOException {

        String expected = "{\"error\":\"Invalid access token\"}";
        String actual = pucturesListParser.getLoadAllImagesFortest().errorBody().string();
        assertEquals("getAllUserImagesTestErrorIdntLogin", expected, actual);
    }


    @Test
    public void getAllUserImagesTest() throws IOException {
        //TODO
        RetrofitClient retrofitClient = new RetrofitClient();

        String expected = "GetAllUserImages{imageDTOList=[ImageDTO{id=520, " +
                "imageParamsDTO=ImageParamsDTO{longitude=90.0, latitude=90.0, address='null', weather='Clouds'}, " +
                "smallImageUrlPath='http://api.doitserver.in.ua/upload/images/small/09714781837887065597a41572c08fa9.jpeg', " +
                "bigImageUrlPath='http://api.doitserver.in.ua/upload/images/big/09714781837887065597a41572c08fa9.jpeg'}, " +
                "ImageDTO{id=521, imageParamsDTO=ImageParamsDTO{longitude=90.0, latitude=90.0, address='null', weather='Clouds'}, " +
                "smallImageUrlPath='http://api.doitserver.in.ua/upload/images/small/b437e6674d76d5d77112f55ea3e85daf.jpeg', " +
                "bigImageUrlPath='http://api.doitserver.in.ua/upload/images/big/b437e6674d76d5d77112f55ea3e85daf.jpeg'}, " +
                "ImageDTO{id=522, imageParamsDTO=ImageParamsDTO{longitude=90.0, latitude=90.0, address='null', weather='Clouds'}, " +
                "smallImageUrlPath='http://api.doitserver.in.ua/upload/images/small/3fa10f2aba4b75bd81da4da0c3ec5e35.jpeg', " +
                "bigImageUrlPath='http://api.doitserver.in.ua/upload/images/big/3fa10f2aba4b75bd81da4da0c3ec5e35.jpeg'}]}";


        String token = "7fb2235a56e9d2da72e3bb0be7743689";
        String actual = retrofitClient.serverApi.getAllUserImages(token).execute().body().toString();
        assertEquals("getAllUserImagesTest", expected, actual);
    }

    @Test
    public void getGifTest() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();

        String expected = "GifDTO{gifUrlPath='http://api.doitserver.in.ua/upload/images/gif/92fd93900b776388a0036dbd8a67da07.gif'}";
        String token = "7fb2235a56e9d2da72e3bb0be7743689";
        String actual = retrofitClient.serverApi.getGif(token).execute().body().toString();
        assertEquals("getGifTest", expected, actual);
    }


    @Test
    public void addImageTestErrorInvalid_access_token() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();
        File image = null;
        String description = "";
        String hashtag = "";
        int latitude = 0;
        int longitude = 0;

        String expected = "{\"error\":\"Invalid access token\"}";

        String token = "7fb2235a56e9d2da72e3bb0be774368";
        String actual = retrofitClient.serverApi.addImage(token, image, description, hashtag, latitude, longitude).execute().errorBody().string();
        assertEquals("addImageTestError Invalid access token", expected, actual);
    }


    @Test
    public void addImageTestErrorInvalid_This_value_should_not_be_blank() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();
        File image = null;
        String description = "";
        String hashtag = "";
        int latitude = 0;
        int longitude = 0;

        String expected = "{\"children\":{\"image\":{\"errors\":[\"Please, upload the image.\"]},\"description\":{},\"hashtag\":{},\"latitude\":{},\"longitude\":{}}}";

        String token = "7fb2235a56e9d2da72e3bb0be7743689";
        String actual = retrofitClient.serverApi.addImage(token, image, description, hashtag, latitude, longitude).execute().errorBody().string();
        assertEquals("addImageTestError This value should not be blank", expected, actual);
    }

}