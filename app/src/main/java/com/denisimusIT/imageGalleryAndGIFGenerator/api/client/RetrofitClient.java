package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.BASE_API_URL;

public class RetrofitClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ServerApi serverApi = retrofit.create(ServerApi.class); //Создаем объект, при помощи которого будем выполнять запросы


}
