package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.view.View;

public class LoginContract {

     interface LoginView {
        void buttonAcceptClick(View view);
        void imageViewAvatarClick();
    }

    interface LoginPresenter {
        void loginValidator();
        void onButtonWasClicked();
        void getDataFromModel();
        void onDestroy();
    }

    public interface LoginModel {
        void setLoginDataToAPI();
        void getDataFromAPI();
        //TODO

    }
}
