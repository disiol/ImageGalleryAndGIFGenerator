package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp.Register;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.crateDataBase;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText emailLogin;
    private EditText passwordLogin;
    private TextView textViewUserName;
    private Button buttonAccept;
    private LoginParser loginParser;
    private ImageView imageViewAvatar;
    private ProgressBar progressBar;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        textViewUserName = findViewById(R.id.textViewUserName);
        emailLogin = findViewById(R.id.login_edit_text_mail);
        passwordLogin = findViewById(R.id.login_edit_text_password);
        buttonAccept = findViewById(R.id.button_accept);
        progressBar = findViewById(R.id.progressBarLogin);

        buttonAccept.setOnClickListener(this);
        imageViewAvatar.setOnClickListener(this); //TODO strat image laiyt if user textViewUserName dint = null

        progressBar.setVisibility(ProgressBar.VISIBLE);
        // we create an object for creation and version control of a DB
        crateDataBase(this);
        progressBar.setVisibility(ProgressBar.INVISIBLE);

        loginParser = new LoginParser();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_accept:
                loginParser.login(emailLogin.getText().toString(), passwordLogin.getText().toString(),
                        imageViewAvatar, textViewUserName, this.getApplicationContext(), buttonAccept, progressBar);
                break;
            case R.id.imageViewAvatar:
                Log.d(LOG_TAG, "imageViewAvatar click");
                if (textViewUserName.getText() != null) {
                    //TODO вызвать PicturesList
                } else {
                    Intent intent = new Intent(this, Register.class);
                    startActivity(intent);

                }

                break;

        }

    }
    //TODO add tsrat register asck

}

