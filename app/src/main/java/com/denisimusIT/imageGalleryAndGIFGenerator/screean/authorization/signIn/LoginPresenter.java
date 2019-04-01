package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.model.authorization.signIn.LoginModel;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages.PicturesList;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getAvatarDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getCreationTimeDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

public class LoginPresenter implements LoginContract.LoginPresenter {
    private LoginActivity loginActivityView;
    private LoginData loginData;

    private final LoginModel loginModel;

    private Context context;
    private boolean cancel;


    public LoginPresenter(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

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

            loginValidator(loginData.getEmailLogin(), loginData.getPasswordLogin(),
                    loginData.getButtonAccept(), loginData.getImageViewAvatar(), loginData.getTextViewUserName(),
                    loginData.getProgressBar(), loginData.getFragmentManager());
        }


    }

    @Override
    public void getDataFromModel() {
        //TODO
        String avatarDataFromTableLoginData = getAvatarDataFromTableLoginData(context);

        Uri imageURI = Uri.parse(avatarDataFromTableLoginData);
        getImageForAvatar(imageURI, loginData.getImageViewAvatar());
        loginData.getTextViewUserName().setText(getCreationTimeDataFromTableLoginData(context));
        loginData.getProgressBar().setVisibility(ProgressBar.INVISIBLE);
        loginData.getButtonAccept().setClickable(true);


    }

    @Override
    public void onDestroy() {
        detachView();
        loginActivityView = null;
/** TODO освободить ресурсы
 * Если бы мы работали например с RxJava, в этом классе стоило бы отписываться от подписок
 * Кроме того, при работе с другими методами асинхронного андроида,здесь мы боремся с утечкой контекста
 */

        Log.d(LOG_TAG, "onDestroy() LoginPresenter");
    }


    @Override
    public void loginValidator(EditText emailLogin, EditText passwordLogin, Button buttonAccept, ImageView imageViewAvatar,
                               TextView textViewUserName, ProgressBar progressBar, FragmentManager fragmentManager) {

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
            setLoginDataToAPI(emailLogin, passwordLogin, buttonAccept, imageViewAvatar, textViewUserName,
                    progressBar, fragmentManager);
        }
    }


    private void setLoginDataToAPI(EditText emailLogin, EditText passwordLogin, Button buttonAccept,
                                   ImageView imageViewAvatar, TextView textViewUserName,
                                   ProgressBar progressBar, FragmentManager fragmentManager) {


        loginModel.setLoginDataToAPI(emailLogin, passwordLogin, imageViewAvatar, textViewUserName,
                loginActivityView, buttonAccept, progressBar, fragmentManager);

        getDataFromModel();
        startPicturesListActivity();
        loginData.getProgressBar().setVisibility(ProgressBar.INVISIBLE);

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
