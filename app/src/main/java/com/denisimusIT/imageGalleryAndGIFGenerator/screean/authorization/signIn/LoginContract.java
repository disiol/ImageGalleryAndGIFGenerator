package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;


public class LoginContract {

     interface LoginView {
        void buttonAcceptClick();
        //TODO rename
        void imageViewAvatarClick();
        LoginData getLoginData();
    }

    interface LoginPresenter {
        void onButtonWasClicked();
        void getAvatarData();
        void onDestroy();
    }

}
