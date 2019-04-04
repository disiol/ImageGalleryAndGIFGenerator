package com.denisimusIT.imageGalleryAndGIFGenerator.util.ArletDialog;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.messageAlertDialog;

public class ErorArletdialog {
    private static DialogFragment dialogFragment;
    //TODO
    public static void showErrorAlertDialogNoInternetConnect(FragmentManager supportFragmentManager, Context context) {
        String message = context.getString(R.string.eror_no_internet_connect);
        String ok = context.getString(R.string.Ok);
        dialogFragment = new messageAlertDialog(message, "", ok);
        dialogFragment.show(supportFragmentManager, "dialog");
    }

}
