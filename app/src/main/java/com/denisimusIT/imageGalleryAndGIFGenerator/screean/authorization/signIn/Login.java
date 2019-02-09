package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseComands.crateDataBase;

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
        //TODO add progressBar
       // progressBar.setVisibility(ProgressBar.VISIBLE);
        loginParser.login(emailLogin.getText().toString(), passwordLogin.getText().toString(), imageViewAvatar, textViewUserName, this.getApplicationContext(),progressBar);

        //TODO add tsrat image laiyt if user textViewUserName dint = null
        //TODO add tsrat register asck

    }
}
