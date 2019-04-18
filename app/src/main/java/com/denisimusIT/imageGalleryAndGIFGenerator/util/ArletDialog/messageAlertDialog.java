package com.denisimusIT.imageGalleryAndGIFGenerator.util.ArletDialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

@SuppressLint("ValidFragment")
public class messageAlertDialog extends DialogFragment {
    private String title;
    private String message;
    private String setPositiveButtonText;

    Intent intent;

    @SuppressLint("ValidFragment")
    public messageAlertDialog(String title, String message, String setPositiveButtonText) {
        this.title = title;
        this.message = message;
        this.setPositiveButtonText = setPositiveButtonText;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(setPositiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        builder.setCancelable(true);

        return builder.create();
    }
}