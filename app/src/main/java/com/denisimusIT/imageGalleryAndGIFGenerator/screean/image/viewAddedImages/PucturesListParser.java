package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GetAllUserImages;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.ImageDTO;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getTokenDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class PucturesListParser {
    private RetrofitClient retrofitClient = new RetrofitClient();
    private Response<GetAllUserImages> getImagesResponse;
    List<ImageDTO> imageDTOList;


    public void loadAllImages(final Context context, final RecyclerView recyclerView) throws IOException {
        //TODO add loidig indecator


        String token = getTokenDataFromTableLoginData(context);
        retrofitClient.serverApi.getAllUserImages(token).enqueue(new Callback<GetAllUserImages>() {
            @Override
            public void onResponse(Call<GetAllUserImages> call, Response<GetAllUserImages> response) {
                if (response.isSuccessful()) {

                    getImagesResponse = response;
                    Log.d(LOG_TAG, "load all images getImagesResponse: " + response.body().toString());
                    imageDTOList = response.body().getImageDTOList();//TODO передать в адаптер

                    createListOfImages(recyclerView, context);


                    Log.d(LOG_TAG, "imageDTOList " + imageDTOList.toString());

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
                //TODO

            }
        });


    }

    public void createListOfImages(RecyclerView recyclerView, Context context) {
        int numberOfColumns = 2;
        //Создадим адаптер
        PicturesListItemAdapter picturesListItemAdapter = new PicturesListItemAdapter(imageDTOList);
        //Применим наш адаптер к RecyclerView
        recyclerView.setAdapter(picturesListItemAdapter);
        //И установим LayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(context,numberOfColumns));
    }

    public List<ImageDTO> getImageDTOList() {
        return imageDTOList;
    }


}
