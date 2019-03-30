package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

class LoginData {

    private String emailLogin;
    private String passwordLogin;
    private TextView textViewUserName;
    private Button buttonAccept;
    private ImageView imageViewAvatar;
    private ProgressBar progressBar;
    private Context loginActivityContext;


    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    public void setTextViewUserName(TextView textViewUserName) {
        this.textViewUserName = textViewUserName;
    }

    public void setButtonAccept(Button buttonAccept) {
        this.buttonAccept = buttonAccept;
    }

    public void setImageViewAvatar(ImageView imageViewAvatar) {
        this.imageViewAvatar = imageViewAvatar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setContext(Context loginActivityContext) {
        this.loginActivityContext = loginActivityContext;

    }


    public String getEmailLogin() {
        return emailLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public TextView getTextViewUserName() {
        return textViewUserName;
    }

    public Button getButtonAccept() {
        return buttonAccept;
    }

    public ImageView getImageViewAvatar() {
        return imageViewAvatar;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Context getContext() {
        return loginActivityContext;
    }

    @Override

    public String toString() {
        return "LoginData{" +
                "emailLogin='" + emailLogin + '\'' +
                ", passwordLogin='" + passwordLogin + '\'' +
                ", textViewUserName=" + textViewUserName +
                ", buttonAccept=" + buttonAccept +
                ", imageViewAvatar=" + imageViewAvatar +
                ", progressBar=" + progressBar +
                ", loginActivityContext=" + loginActivityContext +
                '}';
    }
}
