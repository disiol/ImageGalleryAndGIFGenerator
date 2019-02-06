package com.denisimusIT.imageGalleryAndGIFGenerator.api;

import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authorization {
    public static final String MY_LOG = "MyLog";
    RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;


    public String login(String email, String password) {
        //TODO
        retrofitClient.serverApi.login(email, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    //TODO add response to db
                    responseLogin = response.message();
                    Log.d(MY_LOG, "login response: " + responseLogin);

                } else {
                    try {
                        responseLogin = response.errorBody().string();
                        Log.e(MY_LOG, "login errorBody: " + responseLogin);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                //TODO обработать ошибки

            }

        });

        return responseLogin;  // for tests;
    }

    void signIn() {
        //TODO
    }
}
