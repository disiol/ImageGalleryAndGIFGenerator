package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.IMAGE_MEDIA_TYPE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.SELECT_PICTURE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterContract.RegisterView {
    private EditText editTextUserName, editTextEmail, editTextPassword, editTextConfimPassWord;
    private ImageView imageViewRegister;
    private Button buttonRegistrationSignUp;
    private ProgressBar progressBarRegister;

    RegisterParser registerParser = new RegisterParser();
    private FragmentManager supportFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageViewRegister = findViewById(R.id.imageView_sign_up);
        editTextUserName = findViewById(R.id.et_user_name);
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        editTextConfimPassWord = findViewById(R.id.et_confirm_password);
        buttonRegistrationSignUp = findViewById(R.id.bt_registration_sign_up);
        progressBarRegister = findViewById(R.id.progressBarRegister);

        imageViewRegister.setOnClickListener(this);
        buttonRegistrationSignUp.setOnClickListener(this);

        supportFragmentManager = getSupportFragmentManager();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_registration_sign_up:
                buttonRegistrationSignUpClick();
                break;
            case R.id.imageView_sign_up:
                imageViewRegisterClick();

                break;

        }

    }


    @Override
    public void buttonRegistrationSignUpClick() {
        //TODO
        registerParser.register(imageViewRegister, editTextUserName, editTextEmail, editTextPassword,
                editTextConfimPassWord, buttonRegistrationSignUp, progressBarRegister, v, supportFragmentManager);
    }

    @Override
    public void imageViewRegisterClick() {
        selectImage();
    }

    @Override
    public RegisterData getRegisterData() {
        //TODO
        RegisterData registerData = new RegisterData();
        registerData.setEditTextUserName(editTextUserName);
        registerData.setEditTextPassword(editTextPassword);
        registerData.setEditTextConfimPassWord(editTextConfimPassWord);
        registerData.setEditTextEmail(editTextEmail);
        registerData.setImageViewRegister(imageViewRegister);
        registerData.setButtonRegistrationSignUp(buttonRegistrationSignUp);
        registerData.setProgressBarRegister(progressBarRegister);
        registerData.setApplicationContext(this.getApplicationContext());

        return null;
    }


    private void selectImage() {
        //TODO refactoring
        //TODO add select avatar frm camera
        Intent intent = new Intent();
        intent.setType(IMAGE_MEDIA_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                getImageForAvatar(selectedImageUri, imageViewRegister);
                imageViewRegister.setTag(selectedImageUri);


            }
        }
    }


}
