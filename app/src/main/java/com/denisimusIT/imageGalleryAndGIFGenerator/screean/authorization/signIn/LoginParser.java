package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.CreateTheNewUserAlertDialog;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.ErrorAlertDialog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.addDataToTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.clearTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.getAllDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.getAvatarDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.getCreationTimeDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtill.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

class LoginParser {
    private RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;
    private DialogFragment dialogFragment;
    private Context context;

    public void login(String email, String password, final ImageView imageViewAvatar, final TextView textViewUserName,
                        final View view, final Button buttonAccept, final ProgressBar progressBar, final FragmentManager supportFragmentManager) {
        context = view.getContext();


        buttonAccept.setClickable(false);

        if (email.isEmpty() || password.isEmpty()) {
            buttonAccept.setClickable(true);

            showToastError(context, context.getString(R.string.eror_empty_fileds));

        } else {


            progressBar.setVisibility(ProgressBar.VISIBLE);
            retrofitClient.serverApi.login(email, password).enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    if (response.isSuccessful()) {
                        progressBar.setVisibility(ProgressBar.INVISIBLE);

                        clearTableLoginData(context);
                        responseLogin = response.body().toString();
                        Log.d(LOG_TAG, "view response: " + responseLogin);

                        String avatarImageLink = response.body().getAvatarImageLink();
                        String creationTime = response.body().getCreationTime();
                        String token = response.body().getToken();

                        addDataToTableLoginData(context, avatarImageLink, creationTime, token);

                        String avatarDataFromTableLoginData = getAvatarDataFromTableLoginData(context);

                        Uri imageURI = Uri.parse(avatarDataFromTableLoginData);
                        getImageForAvatar(imageURI, imageViewAvatar);
                        textViewUserName.setText(getCreationTimeDataFromTableLoginData(context));
                        //TODO вызвать PicturesList
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        buttonAccept.setClickable(true);


                    } else {
                        try {

                            responseLogin = response.errorBody().string();
                            //TODO finish the text of an error
                            showCreateTheNewUserAlertDialog(supportFragmentManager);
                            Log.e(LOG_TAG, "view errorBody: " + responseLogin);
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            buttonAccept.setClickable(true);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    //TODO  finish the text of an error err connect to internet
                    showErrorAlertDialog(supportFragmentManager);
                    showToastError(context, t.toString());
                    Log.e(LOG_TAG, "view errorBody: " + t.getMessage());
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    buttonAccept.setClickable(true);



                }

            });

        }

    }

    private void showCreateTheNewUserAlertDialog(FragmentManager supportFragmentManager) {
        //TODO made res String
        String message = "Create the new user?";
        String yes = "Yes";
        String no = "No";
        dialogFragment = new CreateTheNewUserAlertDialog(responseLogin, message, yes, no,context);
        dialogFragment.show(supportFragmentManager, "dialog");
    }

    private void showErrorAlertDialog(FragmentManager supportFragmentManager) {
        //TODO made res String
        String message = "There is no connection to the Internet, please check connection";
        String yes = "Ok";
        dialogFragment = new ErrorAlertDialog(responseLogin, message, yes);
        dialogFragment.show(supportFragmentManager, "dialog");
    }


}
