package com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp;

import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

class RegisterData {

    private EditText editTextUserName, editTextEmail, editTextPassword, editTextConfimPassWord;
    private ImageView imageViewRegister;
    private Button buttonRegistrationSignUp;
    private ProgressBar progressBarRegister;
    private FragmentManager fragmentManager;


    public EditText getEditTextUserName() {
        return editTextUserName;
    }

    public void setEditTextUserName(EditText editTextUserName) {
        this.editTextUserName = editTextUserName;
    }

    public EditText getEditTextEmail() {
        return editTextEmail;
    }

    public void setEditTextEmail(EditText editTextEmail) {
        this.editTextEmail = editTextEmail;
    }

    public EditText getEditTextPassword() {
        return editTextPassword;
    }

    public void setEditTextPassword(EditText editTextPassword) {
        this.editTextPassword = editTextPassword;
    }

    public EditText getEditTextConfimPassWord() {
        return editTextConfimPassWord;
    }

    public void setEditTextConfimPassWord(EditText editTextConfimPassWord) {
        this.editTextConfimPassWord = editTextConfimPassWord;
    }

    public ImageView getImageViewRegister() {
        return imageViewRegister;
    }

    public void setImageViewRegister(ImageView imageViewRegister) {
        this.imageViewRegister = imageViewRegister;
    }

    public Button getButtonRegistrationSignUp() {
        return buttonRegistrationSignUp;
    }

    public void setButtonRegistrationSignUp(Button buttonRegistrationSignUp) {
        this.buttonRegistrationSignUp = buttonRegistrationSignUp;
    }

    public ProgressBar getProgressBarRegister() {
        return progressBarRegister;
    }

    public void setProgressBarRegister(ProgressBar progressBarRegister) {
        this.progressBarRegister = progressBarRegister;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "editTextUserName=" + editTextUserName +
                ", editTextEmail=" + editTextEmail +
                ", editTextPassword=" + editTextPassword +
                ", editTextConfimPassWord=" + editTextConfimPassWord +
                ", imageViewRegister=" + imageViewRegister +
                ", buttonRegistrationSignUp=" + buttonRegistrationSignUp +
                ", progressBarRegister=" + progressBarRegister +
                ", fragmentManager=" + fragmentManager +
                '}';
    }
}
