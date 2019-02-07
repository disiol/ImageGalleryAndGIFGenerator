package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.MY_LOG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

public class LoginParser {
    private RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;


    public String login(String email, String password, final ImageView imageViewAvatar) {
        //TODO
        retrofitClient.serverApi.login(email, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    //TODO add response to db
                    responseLogin = response.body().toString();
                    Log.d(MY_LOG, "login response: " + responseLogin);

                    Uri imageURI = Uri.parse(response.body().getAvatarImageLink());
                    getImageForAvatar(imageURI, imageViewAvatar);

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
                //TODO  err no internet
                Log.e(MY_LOG, "login errorBody: " + t.toString());

            }

        });

        return responseLogin;  // for tests;
    }


    void signUp() {
        //TODO
    }
}
