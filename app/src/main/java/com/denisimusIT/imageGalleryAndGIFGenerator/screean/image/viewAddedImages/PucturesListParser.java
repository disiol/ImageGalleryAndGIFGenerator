package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.content.Context;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GetAllUserImages;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.ImageDTO;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.ImageDataCommands.addDataToTableImageData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.ImageDataCommands.getAllDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getTokenDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.ApiUtils.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class PucturesListParser {
    private RetrofitClient retrofitClient = new RetrofitClient();
    private Response<GetAllUserImages> getImagesResponse;


    public void loadAllImages(final Context context) throws IOException {
        //TODO add loidig indecator
        String token = getTokenDataFromTableLoginData(context);
        retrofitClient.serverApi.getAllUserImages(token).enqueue(new Callback<GetAllUserImages>() {
            @Override
            public void onResponse(Call<GetAllUserImages> call, Response<GetAllUserImages> response) {
                if (response.isSuccessful()) {
                    getImagesResponse = response;
                    getImagesResponseToDb(context);
                    getAllDataFromTableLoginData(context);

                    Log.d(LOG_TAG, "load all images getImagesResponse: " + response.body().toString());


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

    public void getImagesResponseToDb(Context context) {
        List<ImageDTO> imageDTOList = getImagesResponse.body().getImageDTOList();
        for (ImageDTO element : imageDTOList) {
            System.out.println(element.getImageParamsDTO());
            String id = String.valueOf(element.getId());
            String latitude = String.valueOf(element.getImageParamsDTO().getLatitude());
            String longitude = String.valueOf(element.getImageParamsDTO().getLongitude());
            String weather = element.getImageParamsDTO().getWeather();
            String smallImageUrlPath = element.getSmallImageUrlPath();
            String bigImageUrlPath = element.getBigImageUrlPath();
            addDataToTableImageData(context, id, latitude, longitude, weather, smallImageUrlPath, bigImageUrlPath);


        }
    }

    public Response<GetAllUserImages> getLoadAllImagesFortest() {
        return getImagesResponse;
    }
}
