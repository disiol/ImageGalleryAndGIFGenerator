package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
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
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.getCreationTimeDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtill.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

class LoginParser {
    private RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;


    public String login(String email, String password, final ImageView imageViewAvatar, final TextView textViewUserName, final Context context, final ProgressBar progressBar) {

        if (email.isEmpty() || password.isEmpty()) {
            showToastError(context, context.getString(R.string.eror_empty_fileds));
        } else {
            //TODO dSet up progress before call


            progressBar.setVisibility(ProgressBar.VISIBLE);
            retrofitClient.serverApi.login(email, password).enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    if (response.isSuccessful()) {
                        progressBar.setVisibility(ProgressBar.INVISIBLE);

                        clearTable(context);
                        responseLogin = response.body().toString();
                        Log.d(LOG_TAG, "context response: " + responseLogin);

                        String avatarImageLink = response.body().getAvatarImageLink();
                        String creationTime = response.body().getCreationTime();
                        String token = response.body().getToken();

                        addDataToTableLoginData(context, avatarImageLink, creationTime, token);
                        getAllDataFromTableLoginData(context);

                        String avatarDataFromTableLoginData = getAvatarDataFromTableLoginData(context);

                        Uri imageURI = Uri.parse(avatarDataFromTableLoginData);
                        getImageForAvatar(imageURI, imageViewAvatar);
                        textViewUserName.setText(getCreationTimeDataFromTableLoginData(context));
                        //TODO вызвать PicturesList
                        progressBar.setVisibility(ProgressBar.INVISIBLE);


                    } else {
                        try {

                            responseLogin = response.errorBody().string();
                            //TODO finish the text of an error
                            showToastError(context, responseLogin);
                            Log.e(LOG_TAG, "context errorBody: " + responseLogin);
                            progressBar.setVisibility(ProgressBar.INVISIBLE);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    //TODO  finish the text of an error err connect to internet
                    showToastError(context, t.toString());
                    Log.e(LOG_TAG, "context errorBody: " + t.toString());


                }

            });

        }

        return responseLogin;  // for tests;
    }


}
