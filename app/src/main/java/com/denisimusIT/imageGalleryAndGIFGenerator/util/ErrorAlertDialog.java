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

import com.denisimusIT.imageGalleryAndGIFGenerator.screean.authorization.signUp.Register;

@SuppressLint("ValidFragment")
public class ErrorAlertDialog extends DialogFragment {
    private String title;
    private String message;
    private String setPositiveButtonText;

    Intent intent;

    @SuppressLint("ValidFragment")
    public ErrorAlertDialog(String title, String message, String setPositiveButtonText) {
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