package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.ExitActivity;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp.Register;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages.PicturesList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.crateDataBase;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.LoginView {

    private EditText emailLogin;
    private EditText passwordLogin;
    private TextView textViewUserName;
    private Button buttonAccept;
    private ImageView imageViewAvatar;
    private ProgressBar progressBar;

    private FragmentManager supportFragmentManager;

    private LoginPresenter loginParser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkPermission()) {
            openActivity();
        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {
                openActivity();
            }
        }


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    //TODO вынести в отдельный класс

    private static final int PERMISSION_REQUEST_CODE = 200;

    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle(getString(R.string.permission_necessary));
                alertBuilder.setMessage(R.string.storage_permission_is_encessary_to_wrote_event);
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {
                    openActivity();
                } else {
                    finish();
                }

            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void openActivity() {
        setContentView(R.layout.activity_login);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        textViewUserName = findViewById(R.id.textViewUserName);
        emailLogin = findViewById(R.id.login_edit_text_mail);
        passwordLogin = findViewById(R.id.login_edit_text_password);
        buttonAccept = findViewById(R.id.button_accept);
        progressBar = findViewById(R.id.progressBarLogin);

        buttonAccept.setOnClickListener(this);
        imageViewAvatar.setOnClickListener(this);

        progressBar.setVisibility(ProgressBar.VISIBLE);

        crateDataBase(this);
        progressBar.setVisibility(ProgressBar.INVISIBLE);

        supportFragmentManager = getSupportFragmentManager();

        loginParser = new LoginPresenter();




    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_accept:
                buttonAcceptClick();

                break;
            case R.id.imageViewAvatar:
                Log.d(LOG_TAG, "imageViewAvatar click");
                imageViewAvatarClick();
                break;

        }

    }

    @Override
    public void buttonAcceptClick() {
        loginParser.attachView(this);
        loginParser.onButtonWasClicked();


    }


    @Override
    public void imageViewAvatarClick() {
        if (imageViewAvatar.getDrawable() == null) {

            Log.d(LOG_TAG, " imageViewAvatar.getDrawable() == null");
            startActivityRegister();

        } else {

            startPicturesListActivity();
            Log.d(LOG_TAG, "strat image laiyt ");

        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseOfResources();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseOfResources();
    }

    @Override
    public LoginData getLoginData() {
        LoginData loginData = new LoginData();
        loginData.setEmailLogin(emailLogin);
        loginData.setPasswordLogin(passwordLogin);
        loginData.setTextViewUserName(textViewUserName);
        loginData.setButtonAccept(buttonAccept);
        loginData.setImageViewAvatar(imageViewAvatar);
        loginData.setProgressBar(progressBar);
        loginData.setFragmentManager(supportFragmentManager);
        loginData.setContext(this.getApplicationContext());

        return loginData;
    }

    private void startActivityRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void startPicturesListActivity() {
        Intent intent = new Intent(this.getApplicationContext(), PicturesList.class);
        startActivity(intent);
    }
   //TODO destoid



    private void releaseOfResources() {
        loginParser.onDestroy();
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, ExitActivity.class);
        startActivity(intent);
    }




}

