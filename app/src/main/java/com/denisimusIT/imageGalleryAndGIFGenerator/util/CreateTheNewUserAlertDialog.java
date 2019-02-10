package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp.Register;

@SuppressLint("ValidFragment")
public class CreateTheNewUserAlertDialog extends DialogFragment {
    private String title;
    private String message;
    private String setPositiveButtonText;
    private String setNegativeButtonText;
    private Context context;

    Intent intent;

    @SuppressLint("ValidFragment")
    public CreateTheNewUserAlertDialog(String title, String message, String setPositiveButtonText, String setNegativeButtonText, Context context) {
        this.title = title;
        this.message = message;
        this.setPositiveButtonText = setPositiveButtonText;
        this.setNegativeButtonText = setNegativeButtonText;
        this.context = context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(setPositiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                intent = new Intent(context, Register.class);
                startActivity(intent);

            }
        });
        builder.setNegativeButton(setNegativeButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}