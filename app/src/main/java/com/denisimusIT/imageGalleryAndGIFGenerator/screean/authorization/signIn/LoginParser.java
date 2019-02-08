package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.addDataToTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.clearTable;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.getAllDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.getAvatarDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtill.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

class LoginParser {
    private RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;


    public String login(String email, String password, final ImageView imageViewAvatar, final Context login) {
        //TODO
        retrofitClient.serverApi.login(email, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {

                    clearTable(login);
                    responseLogin = response.body().toString();
                    Log.d(LOG_TAG, "login response: " + responseLogin);

                    String avatarImageLink = response.body().getAvatarImageLink();//TODO get from table user data;
                    String token = response.body().getToken();
                    addDataToTableLoginData(login, avatarImageLink, token);
                    getAllDataFromTableLoginData(login);

                    String avatarDataFromTableLoginData = getAvatarDataFromTableLoginData(login);

                    Uri imageURI = Uri.parse(avatarImageLink);
                    getImageForAvatar(imageURI, imageViewAvatar);
                    //TODO вызвать PicturesList

                } else {
                    try {

                        responseLogin = response.errorBody().string();
                        //TODO finish the text of an error
                        showToastError(login, responseLogin);
                        Log.e(LOG_TAG, "login errorBody: " + responseLogin);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                //TODO  finish the text of an error err connect to internet
                showToastError(login, t.toString());
                Log.e(LOG_TAG, "login errorBody: " + t.toString());

            }

        });

        return responseLogin;  // for tests;
    }


}
