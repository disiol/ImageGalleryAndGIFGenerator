package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

public class Register extends AppCompatActivity implements View.OnClickListener  {
    TextView textViewImageViewSignUp;
    EditText editTextUserName, editTextEmail, editTextPassword, editTextConfimPassWord;
    ImageView imageViewRegister;
    Button buttonRegistrationSignUp;
    ProgressBar progressBarRegister;


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
        progressBarRegister = findViewById(R.id.progressBarLogin);

        imageViewRegister.setOnClickListener(this);


        //TODO проверку на зполненость обязательніх полей и вод ошибок

    }

    @Override
    public void onClick(View v) {

    }
}
