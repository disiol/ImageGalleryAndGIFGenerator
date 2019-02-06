package com.denisimusIT.imageGalleryAndGIFGenerator.screean.signIn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.Authorization;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText emailLogin;
    private EditText passwordLogin;
    private TextView textViewUserName;
    private Button buttonAccept;
    private Authorization authorization;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //TODO проверку на зполненость обязательніх полей и вод ошибок

        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        textViewUserName = findViewById(R.id.textViewUserName);
        emailLogin = findViewById(R.id.login_edit_text_mail);
        passwordLogin = findViewById(R.id.login_edit_text_password);
        buttonAccept = findViewById(R.id.button_accept);
        buttonAccept.setOnClickListener(this);

        authorization = new Authorization();
    }

    @Override
    public void onClick(View v) {
        authorization.login(emailLogin.getText().toString(),passwordLogin.getText().toString(),imageViewAvatar);

    }
}
