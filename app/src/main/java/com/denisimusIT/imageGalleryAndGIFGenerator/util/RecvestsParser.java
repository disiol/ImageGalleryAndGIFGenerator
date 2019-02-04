package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import android.net.Uri;
import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.IMAGE_MEDIA_TYPE;

public class RecvestsParser {
    public static RequestBody parseStringIntoRequestBody(String string) {
        return RequestBody.create(MediaType.parse("text/plain"), string);
    }

    public static RequestBody getImageRequestBody(File imageFile) {
        return RequestBody.create(MediaType.parse(IMAGE_MEDIA_TYPE), imageFile);
    }


}
