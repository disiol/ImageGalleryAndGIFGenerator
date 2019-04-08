package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp;


public class RegisterContract {

    interface RegisterView {
        void buttonRegistrationSignUpClick();

        void imageViewRegisterClick();

        RegisterData getRegisterData();
    }

    interface RegisterPresenter {
        void onButtonRegistrationSignUpWasClicked();

        void getRegistrationData();

        void onDestroy();
    }

}
