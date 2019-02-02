package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    String baseUrl = "http://api.doitserver.in.ua";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ServerApi serverApi = retrofit.create(ServerApi.class); //Создаем объект, при помощи которого будем выполнять запросы


}
