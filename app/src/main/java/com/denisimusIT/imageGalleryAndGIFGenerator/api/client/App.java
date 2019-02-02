package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static ServerApi serverApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        String baseUrl = "http://api.doitserver.in.ua";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        serverApi = retrofit.create(ServerApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static ServerApi getApi() {
        return serverApi;
    }
}
