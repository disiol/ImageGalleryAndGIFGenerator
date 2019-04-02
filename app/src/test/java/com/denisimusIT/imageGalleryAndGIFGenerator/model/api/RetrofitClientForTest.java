package com.denisimusIT.imageGalleryAndGIFGenerator.model.api;


import com.denisimusIT.imageGalleryAndGIFGenerator.model.api.client.ServerApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.BASE_API_URL;

public class RetrofitClientForTest {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public final ServerApi serverApi = retrofit.create(ServerApi.class);

}
