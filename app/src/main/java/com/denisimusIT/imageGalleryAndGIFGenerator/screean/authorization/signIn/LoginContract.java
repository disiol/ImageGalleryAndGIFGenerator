package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;


public class LoginContract {

     interface LoginView {
        void buttonAcceptClick();
        void imageViewAvatarClick();
        LoginData getLoginData();
    }

    interface LoginPresenter {
        void onButtonWasClicked();
        void getAvatarData();
        void onDestroy();
    }

}
