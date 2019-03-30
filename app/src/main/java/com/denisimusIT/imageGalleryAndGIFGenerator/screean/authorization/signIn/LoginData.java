package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

class LoginData {

    private String emailLogin;
    private String passwordLogin;
    private String textViewUserName;
    private Button buttonAccept;
    private ImageView imageViewAvatar;
    private ProgressBar progressBar;

    public LoginData(String emailLogin, String passwordLogin, String textViewUserName, Button buttonAccept, ImageView imageViewAvatar, ProgressBar progressBar) {
        this.emailLogin = emailLogin;
        this.passwordLogin = passwordLogin;
        this.textViewUserName = textViewUserName;
        this.buttonAccept = buttonAccept;
        this.imageViewAvatar = imageViewAvatar;
        this.progressBar = progressBar;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public String getTextViewUserName() {
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
}
