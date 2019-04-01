package com.denisimusIT.imageGalleryAndGIFGenerator.model.authorization.signIn;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.UserDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn.LoginActivity;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn.LoginContract;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn.LoginPresenter;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.CreateTheNewUserAlertDialog;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.messageAlertDialog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.api.client.ClientApp.getApi;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.addDataToTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.clearTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class LoginModel implements LoginContract.LoginModel {

    private Context context;
    private Response<UserDTO> responseLogin;
    private DialogFragment dialogFragment;
    private String responseLoginErorText;

    @Override
    public Response<UserDTO> setLoginDataToAPI(EditText emailLogin, EditText passwordLogin, final ImageView imageViewAvatar, final TextView textViewUserName,
                                               final LoginActivity view, final Button buttonAccept, final ProgressBar progressBar, final FragmentManager supportFragmentManager) {

        context = view;


        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();
        progressBar.setVisibility(ProgressBar.VISIBLE);
        getApi().login(email, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    clearTableLoginData(context);

                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    clearTableLoginData(context);
                    responseLogin = response;
                    Log.d(LOG_TAG, "view response: " + responseLogin);
                    getDataFromAPI(responseLogin.body());

                } else {
                    try {

                        responseLoginErorText = response.errorBody().string();
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

        return responseLogin;
    }


    private void getDataFromAPI(UserDTO responseLoginBody) {
        //TODO
        String avatarImageLink = responseLoginBody.getAvatarImageLink();
        String creationTime = responseLoginBody.getCreationTime();
        String token = responseLoginBody.getToken();
        addDataToTableLoginData(context, avatarImageLink, creationTime, token);

    }


    //TODO
    private void showErrorAlertDialog(FragmentManager supportFragmentManager) {
        //TODO made res String
        String message = "There is no connection to the Internet, please check connection";
        String yes = "Ok";
        dialogFragment = new messageAlertDialog(responseLoginErorText, message, yes);
        dialogFragment.show(supportFragmentManager, "dialog");
    }


    private void showCreateTheNewUserAlertDialog(FragmentManager supportFragmentManager) {
        //TODO made res String
        String message = "Create the new user?";
        String yes = "Yes";
        String no = "No";
        dialogFragment = new CreateTheNewUserAlertDialog(responseLoginErorText, message, yes, no, context);
        dialogFragment.show(supportFragmentManager, "dialog");
    }

}



