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
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signIn.Login;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.PathUtil;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.messageAlertDialog;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.ApiUtils.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getFile;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.getImageRequestBody;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.parseStringIntoRequestBody;

public class RegisterParser {

    private RetrofitClient retrofitClient = new RetrofitClient();

    private DialogFragment dialogFragment;
    private Context context;
    private boolean cancel;
    private String title;


    public void register(final ImageView imageViewRegister,
                         EditText editTextUserName,
                         EditText editTextEmail,
                         EditText editTextPassword,
                         EditText editTextConfimPassWord,
                         final Button buttonRegistrationSignUp,
                         final ProgressBar progressBarRegister,
                         View view, final FragmentManager supportFragmentManager) {

        context = view.getContext();
        cancel = false;
        buttonRegistrationSignUp.setClickable(false);
        imageViewRegister.setClickable(false);


        resetErrors(editTextEmail, editTextPassword, editTextConfimPassWord);

        String userName = editTextUserName.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        String confirmPassWord = editTextConfimPassWord.getText().toString();

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
            //TODO
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
            Log.e(LOG_TAG, "tray response ");

            //TODO
            Uri imageViewRegisterTag = (Uri) imageViewRegister.getTag();
            File imageFile = new File(PathUtil.getPath(context.getApplicationContext(), imageViewRegisterTag));
            RequestBody avatar = getImageRequestBody(imageFile);
            MultipartBody.Part avatarBody = (MultipartBody.Part) MultipartBody.Part.createFormData("avatar", imageFile.getName(), avatar);


            progressBarRegister.setVisibility(ProgressBar.VISIBLE);


            retrofitClient.serverApi.createNewUser(parseStringIntoRequestBody(userName),
                    parseStringIntoRequestBody(email),
                    parseStringIntoRequestBody(password),
                    avatarBody).enqueue(new Callback<Response<UserDTO>>() {
                @Override
                public void onResponse(Call<Response<UserDTO>> call, Response<Response<UserDTO>> response) {
                    progressBarRegister.setVisibility(ProgressBar.VISIBLE);


                    String title = "New user created";
                    Log.e(LOG_TAG, "reg response " + title);

                    showToastError(context, title);

                    startLoginAtyvity();
                    buttonRegistrationSignUp.setClickable(true);
                    imageViewRegister.setClickable(true);
                    progressBarRegister.setVisibility(ProgressBar.INVISIBLE);


                }

                @Override
                public void onFailure(Call<Response<UserDTO>> call, Throwable t) {
                    //TODO  finish the text of an error err connect to internet
                    String title = t.toString();
                    String message = "There is no connection to the Internet, please check connection";
                    showAlertDialog(supportFragmentManager, title, message);
                    showToastError(context, t.toString());
                    Log.e(LOG_TAG, "view errorBody: " + t.getMessage());
                    progressBarRegister.setVisibility(ProgressBar.INVISIBLE);


                }
            });
            Log.d(LOG_TAG, "reg ok");
        }
    }

    private void startLoginAtyvity() {
        Intent intent = new Intent(context.getApplicationContext(), Login.class);
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

