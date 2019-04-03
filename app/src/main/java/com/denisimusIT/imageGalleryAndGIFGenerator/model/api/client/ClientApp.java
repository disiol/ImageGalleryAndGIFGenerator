package com.denisimusIT.imageGalleryAndGIFGenerator.model.api.client;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.BASE_API_URL;

public class ClientApp extends MultiDexApplication {

    private static ServerApi serverApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();


        retrofitClient();

        serverApi = retrofit.create(ServerApi.class);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }



    public static ServerApi getApi() {
        return serverApi;
    }

    private void retrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
