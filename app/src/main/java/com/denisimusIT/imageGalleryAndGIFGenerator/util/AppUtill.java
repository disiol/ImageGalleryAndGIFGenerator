package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import android.content.Context;
import android.widget.Toast;

public class AppUtill {
    public static void showToastError(Context context, String errorText) {
        Toast toast = Toast.makeText(context, errorText, Toast.LENGTH_LONG);
        toast.show();
    }
}
