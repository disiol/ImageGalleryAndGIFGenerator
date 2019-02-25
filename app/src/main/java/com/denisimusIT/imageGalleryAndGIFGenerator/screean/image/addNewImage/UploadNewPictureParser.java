package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.addNewImage;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.PathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getTokenDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.getImageRequestBody;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.parseStringIntoRequestBody;

public class UploadNewPictureParser {

    private RetrofitClient retrofitClient = new RetrofitClient();
    private Context applicationContext;

    public void addImage(final Context context, ImageView imageViewUploadImage, final ProgressBar progressBarUploadimage,
                         EditText editTextDescription, EditText editTextHashTag, String latitudeText, String longitudeText) {


        applicationContext = context;


        progressBarUploadimage.setVisibility(View.VISIBLE);

        String token = getTokenDataFromTableLoginData(applicationContext);

        Uri imageViewUploadImageTag = (Uri) imageViewUploadImage.getTag();
        File imageFile = new File(PathUtil.getPath(applicationContext, imageViewUploadImageTag));
        RequestBody imageRequestBody = getImageRequestBody(imageFile);
        MultipartBody.Part imageBody = (MultipartBody.Part) MultipartBody.Part.createFormData("imageRequestBody", imageFile.getName(), imageRequestBody);


        RequestBody description = parseStringIntoRequestBody(editTextDescription.getText().toString());
        RequestBody hashTag = parseStringIntoRequestBody(editTextHashTag.getText().toString());
        RequestBody latitude = parseStringIntoRequestBody(latitudeText);
        RequestBody longitude = parseStringIntoRequestBody(longitudeText);

        retrofitClient.serverApi.addImage(token, imageBody, description, hashTag, latitude, longitude).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Log.d(LOG_TAG, "responseAddImage: " + response.toString());
                    progressBarUploadimage.setVisibility(View.INVISIBLE);

                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.d(LOG_TAG, "responseAddImageEror: " + errorBody);
                        AppUtil.showToastError(applicationContext, errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //TODO

            }
        });

    }


}
