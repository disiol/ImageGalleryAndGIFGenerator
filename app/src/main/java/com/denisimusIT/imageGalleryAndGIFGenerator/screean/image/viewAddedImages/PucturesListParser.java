package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.GetAllUserImagesDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.ImageDTO;

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
    private Response<GetAllUserImagesDTO> getImagesResponse;
    List<ImageDTO> imageDTOList;


    public void loadAllImages(final Context context, final RecyclerView recyclerView) {
        //TODO add loidig indecator


        String token = getTokenDataFromTableLoginData(context);
        retrofitClient.serverApi.getAllUserImages(token).enqueue(new Callback<GetAllUserImagesDTO>() {
            @Override
            public void onResponse(Call<GetAllUserImagesDTO> call, Response<GetAllUserImagesDTO> response) {
                if (response.isSuccessful()) {

                    getImagesResponse = response;
                    Log.d(LOG_TAG, "load all images getImagesResponse: " + response.body().toString());
                    imageDTOList = response.body().getImageDTOList();//TODO передать в адаптер

                    int numberOfColumns = 2;
                    //Создадим адаптер
                    PicturesListItemAdapter picturesListItemAdapter = new PicturesListItemAdapter(imageDTOList);
                    //Применим наш адаптер к RecyclerView
                    recyclerView.setAdapter(picturesListItemAdapter);
                    //И установим LayoutManager
                    recyclerView.setLayoutManager(new GridLayoutManager(context, numberOfColumns));


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
            public void onFailure(Call<GetAllUserImagesDTO> call, Throwable t) {
                //TODO

            }
        });


    }

    public List<ImageDTO> getImageDTOList() {
        return imageDTOList;
    }


    public Response getLoadAllImagesFortest() {
        return getImagesResponse;
    }
}
