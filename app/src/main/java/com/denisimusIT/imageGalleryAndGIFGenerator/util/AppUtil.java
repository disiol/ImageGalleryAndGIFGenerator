package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.io.File;
import java.net.URLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AppUtil extends DialogFragment {
    public static void showToastError(Context context, String errorText) {
        Toast toast = Toast.makeText(context, errorText, Toast.LENGTH_LONG);
        toast.show();
    }

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    @NonNull
    public static MultipartBody.Part prepareFilePart(String partName, File file) {

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        RequestBody requestFile = RequestBody.create(MediaType.parse(mimeType), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public static boolean isStringNotEmpty(String string){
        return string != null && string.length() > 0;
    }
}
