package com.denisimusIT.imageGalleryAndGIFGenerator.api;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;

public class ConnectToClientSingleton {
    private static final RetrofitClient RETROFIT_CLIENT = new RetrofitClient();

    public static RetrofitClient getInstance() {
        return RETROFIT_CLIENT;
    }
}
