package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.UserDTO;

import retrofit2.Response;

public class LoginContract {

     interface LoginView {
        void buttonAcceptClick();
        void imageViewAvatarClick();
        LoginData getLoginData();
    }

    interface LoginPresenter {
        void loginValidator(EditText emailLogin, EditText passwordLogin, Button buttonAccept, ImageView imageViewAvatar,
                            TextView textViewUserName, ProgressBar progressBar, FragmentManager fragmentManager);
        void onButtonWasClicked();
        void getDataFromModel();
        void onDestroy();
    }

    public interface LoginModel {
        Response<UserDTO> setLoginDataToAPI(EditText emailLogin, EditText passwordLogin, final ImageView imageViewAvatar, final TextView textViewUserName,
                                            final LoginActivity view, final Button buttonAccept, final ProgressBar progressBar, final FragmentManager supportFragmentManager);
        //TODO

    }
}
