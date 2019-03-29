package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import org.junit.Test;

import retrofit2.Retrofit;

import static org.junit.Assert.assertTrue;

public class RetrofitClientAppSingletonTest {


    private ServerApi singleton1 =  ClientApp.getApi();
    private ServerApi singleton2 =  ClientApp.getApi();;
    private ServerApi singleton3 =  ClientApp.getApi();;


    @Test
    public void Only_one_copy_was_created_Test() {
        assertTrue("Only one copy was created", singleton1 == singleton2 && singleton2 == singleton3);
    }
}
