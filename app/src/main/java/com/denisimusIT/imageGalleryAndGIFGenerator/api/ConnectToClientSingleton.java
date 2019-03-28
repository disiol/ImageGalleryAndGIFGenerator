package com.denisimusIT.imageGalleryAndGIFGenerator.api;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;

public class ConnectToClientSingleton {
    private static final ConnectToClientSingleton ourInstance = new ConnectToClientSingleton();

    public static ConnectToClientSingleton getInstance() {
        return ourInstance;
    }

    private ConnectToClientSingleton() {
        RetrofitClient retrofitClient = new RetrofitClient();

    }
}
