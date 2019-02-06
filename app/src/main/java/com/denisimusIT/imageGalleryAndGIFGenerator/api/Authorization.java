package com.denisimusIT.imageGalleryAndGIFGenerator.api;

import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.MY_LOG;

public class Authorization {
    private RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;


    public String login(String email, String password) {
        //TODO
        retrofitClient.serverApi.login(email, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    //TODO add response to db
                    responseLogin = response.body().toString();
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
                    //TODO how err ni internet
                Log.e(MY_LOG, "login errorBody: " + t.toString());

            }

        });

        return responseLogin;  // for tests;
    }

    void signIn() {
        //TODO
    }
}
