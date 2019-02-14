package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.ApiUtils.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class RegisterParser {

    private RetrofitClient retrofitClient = new RetrofitClient();

    private String responseLogin;
    private DialogFragment dialogFragment;
    private Context context;
    private boolean cancel;


    public void register(ImageView imageViewRegister,
                         EditText editTextUserName,
                         EditText editTextEmail,
                         EditText editTextPassword,
                         EditText editTextConfimPassWord,
                         Button buttonRegistrationSignUp,
                         ProgressBar progressBarRegister,
                         View view) {

        context = view.getContext();

        resetErrors(editTextEmail, editTextPassword, editTextConfimPassWord);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String ConfimPassWord = editTextConfimPassWord.getText().toString();

        cancel = true;
        View focusView = null;

        buttonRegistrationSignUp.setClickable(false);
        imageViewRegister.setClickable(false);

        if (email.isEmpty() && password.isEmpty() && ConfimPassWord.isEmpty() && imageViewRegister.getDrawable() == null) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_empty_files_email_password_confim_pass_word_avatar));
            focusView = imageViewRegister;

            editTextEmail.setError(context.getString(R.string.error_field_required));
            editTextPassword.setError(context.getString(R.string.error_field_required));
            editTextConfimPassWord.setError(context.getString(R.string.error_field_required));

            cancel = true;

        } else if (email.isEmpty() && password.isEmpty() && ConfimPassWord.isEmpty()) {
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);
            showToastError(context, context.getString(R.string.error_empty_files_email_password));

            focusView = editTextEmail;
            editTextEmail.setError(context.getString(R.string.error_field_required));
            editTextPassword.setError(context.getString(R.string.error_field_required));
            editTextConfimPassWord.setError(context.getString(R.string.error_field_required));

            cancel = true;


        } else if (password.isEmpty() && ConfimPassWord.isEmpty()) {
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


        }else if (ConfimPassWord.isEmpty()) {
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
        }

        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus();
            }
            buttonRegistrationSignUp.setClickable(true);
            imageViewRegister.setClickable(true);


        } else {

            Log.d(LOG_TAG, "reg ok");
            //TODO
        }
    }

    private void resetErrors(EditText editTextEmail, EditText editTexetPassWord, EditText editTextConfimPassWord) {
        editTextEmail.setError(null);
        editTexetPassWord.setError(null);
        editTextConfimPassWord.setError(null);


    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
}

