package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.content.Context;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GetAllUserImages;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getTokenDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.ApiUtils.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class PucturesListParser {
    private RetrofitClient retrofitClient = new RetrofitClient();
    private Response<GetAllUserImages> getAllUserImagesResponse;



    public void loadAllImages(final Context context) throws IOException {

        String token = getTokenDataFromTableLoginData(context); //TODO get from table user data
        retrofitClient.serverApi.getAllUserImages(token).enqueue(new Callback<GetAllUserImages>() {
         @Override
         public void onResponse(Call<GetAllUserImages> call, Response<GetAllUserImages> response) {
             if (response.isSuccessful()) {
                 //TODO add getAllUserImagesResponse to db
                 getAllUserImagesResponse = response;
                 Log.d(LOG_TAG, "load all images getAllUserImagesResponse: " + response.body().toString());


             } else {
                 try {

                     response = response;
                     //TODO finish the text of an error
                     showToastError(context, response.errorBody().string());
                     Log.e(LOG_TAG, "context errorBody: " + response);

                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

         }

         @Override
         public void onFailure(Call<GetAllUserImages> call, Throwable t) {

         }
     });//TODO



    }

    public Response<GetAllUserImages> getLoadAllImagesFortest() {
        return getAllUserImagesResponse;
    }
}
