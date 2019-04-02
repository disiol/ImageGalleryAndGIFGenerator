package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.model.api.dto.UserDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages.PicturesList;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.CreateTheNewUserAlertDialog;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.messageAlertDialog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.model.api.client.ClientApp.getApi;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.addDataToTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.clearTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getAvatarDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getCreationTimeDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

public class LoginPresenter implements LoginContract.LoginPresenter {
    private LoginActivity loginActivityView;
    private LoginData loginData;

    private Context context;
    private boolean cancel;
    private Response<UserDTO> responseLogin;
    private DialogFragment dialogFragment;
    private String responseLoginErorText;


    public void attachView(LoginActivity loginActivity) {
        loginActivityView = loginActivity;
    }

    private void detachView() {
        loginActivityView = null;
        this.context = null;
    }


    @Override
    public void onButtonWasClicked() {
        loginData = loginActivityView.getLoginData();
        context = loginData.getContext();
        if (context != null) {

            loginValidator(loginData.getEmailLogin(), loginData.getPasswordLogin(), loginData.getButtonAccept(),
                    loginData.getFragmentManager(), loginData.getProgressBar());
        }


    }


    @Override
    public void onDestroy() {
        detachView();
        loginActivityView = null;
        Log.d(LOG_TAG, "onDestroy() LoginPresenter");
    }



    private void loginValidator(EditText emailLogin, EditText passwordLogin, Button buttonAccept,
                               FragmentManager fragmentManager, ProgressBar progressBar) {

        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();
        resetErrors(emailLogin, passwordLogin);


        cancel = false;
        View focusView = null;

        buttonAccept.setClickable(false);

        if (email.isEmpty() || password.isEmpty()) {
            buttonAccept.setClickable(true);
            showToastError(context, context.getString(R.string.error_empty_files_email_password));
            cancel = true;

        } else if (TextUtils.isEmpty(email)) {
            emailLogin.setError(context.getString(R.string.error_field_required));
            focusView = emailLogin;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailLogin.setError(context.getString(R.string.error_invalid_email));
            focusView = emailLogin;
            cancel = true;
        }

        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus();
            }
            buttonAccept.setClickable(true);

        } else {
            setLoginDataToAPI(emailLogin, passwordLogin, buttonAccept, progressBar, fragmentManager);
        }
    }


    private void setLoginDataToAPI(EditText emailLogin, EditText passwordLogin, final Button buttonAccept,
                                   final ProgressBar progressBar, final FragmentManager supportFragmentManager) {


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
                    startPicturesListActivity();
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    buttonAccept.setClickable(true);

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
    }


    private void getDataFromAPI(UserDTO responseLoginBody) {
        //TODO
        String avatarImageLink = responseLoginBody.getAvatarImageLink();
        String creationTime = responseLoginBody.getCreationTime();
        String token = responseLoginBody.getToken();
        addDataToTableLoginData(context, avatarImageLink, creationTime, token);

    }

    @Override
    public void getAvatarData() {
        //TODO
        String avatarDataFromTableLoginData = getAvatarDataFromTableLoginData(context);
        Uri imageURI = Uri.parse(avatarDataFromTableLoginData);
        getImageForAvatar(imageURI, loginData.getImageViewAvatar());
        loginData.getTextViewUserName().setText(getCreationTimeDataFromTableLoginData(context));
        loginData.getProgressBar().setVisibility(ProgressBar.INVISIBLE);
        loginData.getButtonAccept().setClickable(true);


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


    private void resetErrors(EditText emailLogin, EditText passwordLogin) {
        emailLogin.setError(null);
        passwordLogin.setError(null);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }


    private void startPicturesListActivity() {
        Intent intent = new Intent(context, PicturesList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
