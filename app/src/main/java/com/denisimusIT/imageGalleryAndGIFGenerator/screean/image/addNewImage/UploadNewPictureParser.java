package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.addNewImage;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
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

import static android.app.Activity.RESULT_OK;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getTokenDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.isStringNotEmpty;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.getImageRequestBody;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.parseStringIntoRequestBody;

public class UploadNewPictureParser {

    private RetrofitClient retrofitClient = new RetrofitClient();
    private Context applicationContext;
    private ProgressBar progressBar;
    private MultipartBody.Part image;

    public void addImage(final ImageView imageViewUploadImage, final ProgressBar progressBarUploadimage,
                         final EditText editTextDescription, final EditText editTextHashTag, String latitudeText, String longitudeText) {

        progressBar = progressBarUploadimage;

        applicationContext = UploadNewPicture.getContext();

        progressBar(imageViewUploadImage, editTextDescription, editTextHashTag, progressBarUploadimage);


        progressBarUploadimage.setVisibility(View.VISIBLE);

        String token = getTokenDataFromTableLoginData(applicationContext);

        if (imageViewUploadImage.getDrawable() == null |
                !isStringNotEmpty(String.valueOf(imageViewUploadImage.getTag())) |
                !isStringNotEmpty(latitudeText) |
                !isStringNotEmpty(longitudeText)) {

            AppUtil.showToastError(applicationContext, applicationContext.getResources().getString(R.string.pick_up_image_error));

            enableInputs(imageViewUploadImage, editTextDescription, editTextHashTag, progressBarUploadimage);


        } else {

            MultipartBody.Part imageBody = getUplodImage(imageViewUploadImage);
            RequestBody description = getDescriptionText(editTextDescription);
            RequestBody hashTag = getHashTagText(editTextHashTag);
            RequestBody latitude = parseStringIntoRequestBody(latitudeText);
            RequestBody longitude = parseStringIntoRequestBody(longitudeText);

            retrofitClient.serverApi.addImage(token, imageBody, description, hashTag, latitude, longitude).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {
                        Log.d(LOG_TAG, "responseAddImage: " + response.toString());
                        progressBarUploadimage.setVisibility(View.VISIBLE);

                    } else {
                        try {
                            String errorBody = response.errorBody().string();
                            Log.e(LOG_TAG, "responseAddImageEror: " + errorBody);
                            AppUtil.showToastError(applicationContext, errorBody);
                            enableInputs(imageViewUploadImage, editTextDescription, editTextHashTag, progressBarUploadimage);
                            progressBarUploadimage.setVisibility(View.INVISIBLE);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //TODO
                    Log.e(LOG_TAG, "responseAddImageEror: " + t.toString());
                    enableInputs(imageViewUploadImage, editTextDescription, editTextHashTag, progressBarUploadimage);
                    progressBar.setVisibility(View.INVISIBLE);

                }


            });

        }
    }

    public RequestBody getHashTagText(EditText editTextHashTag) {

        String hashTagText = editTextHashTag.getText().toString();

        if (isStringNotEmpty(hashTagText)) {
            return parseStringIntoRequestBody(hashTagText);
        }
        return null;
    }

    public RequestBody getDescriptionText(EditText editTextDescription) {
        String desciptionText = editTextDescription.getText().toString();
        if (isStringNotEmpty(desciptionText)) {
            return parseStringIntoRequestBody(desciptionText);
        }
        return null;
    }


    public MultipartBody.Part getUplodImage(ImageView imageViewUploadImage) {
        Uri imageViewUploadImageTag = (Uri) imageViewUploadImage.getTag();
        if (isStringNotEmpty(String.valueOf(imageViewUploadImageTag))) {
            File imageFile = new File(PathUtil.getPath(applicationContext, imageViewUploadImageTag));
            RequestBody uplodImage = getImageRequestBody(imageFile);
            return (MultipartBody.Part) MultipartBody.Part.createFormData("image", imageFile.getName(), uplodImage);
        }
        return null;
    }


    private void progressBar(ImageView imageViewUploadImage, EditText editTextDescription,
                             EditText editTextHashTag, ProgressBar progressBarUploadImage) {
        imageViewUploadImage.setEnabled(false);
        editTextDescription.setEnabled(false);
        editTextHashTag.setEnabled(false);
        progressBarUploadImage.setVisibility(View.VISIBLE);
    }

    private void enableInputs(ImageView imageViewUploadImage, EditText editTextDescription,
                              EditText editTextHashTag, ProgressBar progressBarUploadImage) {
        imageViewUploadImage.setEnabled(true);
        editTextDescription.setEnabled(true);
        editTextHashTag.setEnabled(true);
        progressBarUploadImage.setVisibility(View.INVISIBLE);
    }


}
