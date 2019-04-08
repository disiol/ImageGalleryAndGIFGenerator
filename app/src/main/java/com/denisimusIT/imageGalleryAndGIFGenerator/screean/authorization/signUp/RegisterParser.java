package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.model.api.dto.UserDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn.LoginActivity;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.PathUtil;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.messageAlertDialog;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.model.api.client.ClientApp.getApi;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.ArletDialog.ErrorAlertDialog.showErrorAlertDialogNoInternetConnect;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.getImageRequestBody;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.parseStringIntoRequestBody;


public class RegisterParser implements RegisterContract.RegisterPresenter {


    private DialogFragment dialogFragment;
    private Context context;
    private boolean cancel;
    private String responseErorrBody;
    private String responseMessage;
    private RegisterActivity registerActivityView;
    private RegisterData registerData;

    private String userName;
    private String email;
    private String password;
    private String confirmPassWord;

    private ImageView imageViewRegister;
    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfimPassWord;
    private Button buttonRegistrationSignUp;
    private ProgressBar progressBarRegister;
    private FragmentManager supportFragmentManager;


    public void attachView(RegisterActivity registerActivity) {
        registerActivityView = registerActivity;
    }

    private void detachView() {
        registerActivityView = null;
        this.context = null;
    }


    @Override
    public void onButtonRegistrationSignUpWasClicked() {
        //TODO
        getRegistrationData();
        register();
    }


    private void getRegistrationData() {
        //TODO dell metod?
        RegisterData registerData = registerActivityView.getRegisterData();

        if (registerData != null) {
            imageViewRegister = registerData.getImageViewRegister();
            editTextUserName = registerData.getEditTextUserName();
            editTextEmail = registerData.getEditTextEmail();
            editTextPassword = registerData.getEditTextPassword();
            editTextConfimPassWord = registerData.getEditTextConfimPassWord();
            buttonRegistrationSignUp = registerData.getButtonRegistrationSignUp();
            progressBarRegister = registerData.getProgressBarRegister();
            supportFragmentManager = registerData.getFragmentManager();
        }


    }

    @Override
    public void onDestroy() {
        //TODO  disroed usedge resursers

        detachView();
    }


    public void register() {

        boolean registerValidator = registorValidator(imageViewRegister, editTextUserName, editTextEmail,
                editTextPassword, editTextConfimPassWord, buttonRegistrationSignUp, registerActivityView);

        if (registerValidator) {
            setDataToAPI(imageViewRegister, buttonRegistrationSignUp, progressBarRegister, supportFragmentManager);
        } else {
            //do nothing
        }

    }


    private boolean registorValidator(ImageView imageViewRegister,
                                      EditText editTextUserName, EditText editTextEmail,
                                      EditText editTextPassword, EditText editTextConfimPassWord,
                                      Button buttonRegistrationSignUp, RegisterActivity registerActivity) {

        context = registerActivity.getApplicationContext();

        //TODO refactoring
        cancel = false;
        buttonRegistrationSignUp.setClickable(false);
        imageViewRegister.setClickable(false);


        resetErrors(editTextEmail, editTextPassword, editTextConfimPassWord);

        userName = editTextUserName.getText().toString();
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        confirmPassWord = editTextConfimPassWord.getText().toString();

        View focusView = null;


        if (email.isEmpty() && password.isEmpty() && confirmPassWord.isEmpty() && imageViewRegister.getDrawable() == null) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_empty_files_email_password_confim_pass_word_avatar));
            focusView = imageViewRegister;

            editTextEmail.setError(context.getString(R.string.error_field_required));
            editTextPassword.setError(context.getString(R.string.error_field_required));
            editTextConfimPassWord.setError(context.getString(R.string.error_field_required));

            cancel = true;

        } else if (email.isEmpty() && password.isEmpty() && confirmPassWord.isEmpty()) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_empty_files_email_password));

            focusView = editTextEmail;
            editTextEmail.setError(context.getString(R.string.error_field_required));
            editTextPassword.setError(context.getString(R.string.error_field_required));
            editTextConfimPassWord.setError(context.getString(R.string.error_field_required));

            cancel = true;


        } else if (password.isEmpty() && confirmPassWord.isEmpty()) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_password_confim_pass_word));

            focusView = editTextPassword;
            editTextPassword.setError(context.getString(R.string.error_field_required));
            editTextConfimPassWord.setError(context.getString(R.string.error_field_required));

            cancel = true;


        } else if (password.isEmpty()) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_confim_pass_word));

            focusView = editTextPassword;
            editTextPassword.setError(context.getString(R.string.error_empty_password));

            cancel = true;


        } else if (confirmPassWord.isEmpty()) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_confim_pass_word));

            focusView = editTextConfimPassWord;
            editTextConfimPassWord.setError(context.getString(R.string.error_field_required));

            cancel = true;


        } else if (imageViewRegister.getDrawable() == null) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_empty_avatar));
            cancel = true;


        } else if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(context.getString(R.string.error_field_required));
            focusView = editTextEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            editTextEmail.setError(context.getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            cancel = true;
        } else if (!password.equals(confirmPassWord)) {
            editTextConfimPassWord.setError(context.getString(R.string.error_passwords_should_match));
            editTextPassword.setError(context.getString(R.string.error_passwords_should_match));
            focusView = editTextPassword;
            cancel = true;
        }


        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus();
            }
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);


        } else {
            return true;
        }
        return false;
    }


    private void setDataToAPI(final ImageView imageViewRegister,
                              final Button buttonRegistrationSignUp,
                              final ProgressBar progressBarRegister,
                              final FragmentManager supportFragmentManager) {

        Log.e(LOG_TAG, "tray response ");

        //TODO
        Uri imageViewRegisterTag = (Uri) imageViewRegister.getTag();
        File imageFile = new File(PathUtil.getPath(registerActivityView, imageViewRegisterTag));
        RequestBody avatar = getImageRequestBody(imageFile);
        MultipartBody.Part avatarBody = MultipartBody.Part.createFormData("avatar", imageFile.getName(), avatar);


        progressBarRegister.setVisibility(ProgressBar.VISIBLE);


        getApi().createNewUser(parseStringIntoRequestBody(this.userName),
                parseStringIntoRequestBody(this.email),
                parseStringIntoRequestBody(this.password),
                avatarBody).enqueue(new Callback<Response<UserDTO>>() {
            @Override
            public void onResponse(Call<Response<UserDTO>> call, Response<Response<UserDTO>> response) {

                progressBarRegister.setVisibility(ProgressBar.VISIBLE);

                if (response.isSuccessful()) {
                    //TODO refactoring
                    String title = "New user created";
                    Log.e(LOG_TAG, "reg response " + title);
                    showToastError(context, title);

                    startLoginAtyvity();
                    //TODO refactoring
                    buttonRegistrationSignUp.setClickable(true);
                    imageViewRegister.setClickable(true);
                    progressBarRegister.setVisibility(ProgressBar.INVISIBLE);
                } else {


                    try {
                        responseErorrBody = response.errorBody().string();
                        responseMessage = response.message();
                        //TODO finish the text of an error
                        Log.e(LOG_TAG, "responseError " + responseErorrBody);
                        //TODO finish the text of an error
                        showAlertDialog(supportFragmentManager, responseMessage, responseErorrBody);
                        cancel = true;
                        buttonRegistrationSignUp.setClickable(true);
                        imageViewRegister.setClickable(true);
                        progressBarRegister.setVisibility(ProgressBar.INVISIBLE);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }

            }

            @Override
            public void onFailure(Call<Response<UserDTO>> call, Throwable t) {

                String messageEror = t.getMessage();
                Log.e(LOG_TAG, "view errorBody: " + messageEror);
                showErrorAlertDialogNoInternetConnect(supportFragmentManager,context);

                //TODO refactoring
                buttonRegistrationSignUp.setClickable(true);
                imageViewRegister.setClickable(true);
                progressBarRegister.setVisibility(ProgressBar.INVISIBLE);

            }
        });


        Log.d(LOG_TAG, "reg ok");

    }


    private void startLoginAtyvity() {
        Intent intent = new Intent(context.getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void resetErrors(EditText editTextEmail, EditText editTextPassWord, EditText editTextConfimPassWord) {
        editTextEmail.setError(null);
        editTextPassWord.setError(null);
        editTextConfimPassWord.setError(null);


    }

    private void showAlertDialog(FragmentManager supportFragmentManager, String title, String message) {
        //TODO made res String
        String yes = "Ok";
        dialogFragment = new messageAlertDialog(title, message, yes);
        dialogFragment.show(supportFragmentManager, "dialog");
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }


}

