package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.view.View;

public class LoginContract {

     interface LoginView {
        void buttonAcceptClick(View view);
        void imageViewAvatarClick();
    }

    interface LoginPresenter {
        void onButtonWasClicked();
        void onDestroy();
    }

    interface LoginModel {
        String loadMessage();
    }
}
