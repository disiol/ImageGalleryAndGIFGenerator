package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.generateGIF;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.RetrofitClient;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GetAllUserImages;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GifDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages.PicturesListItemAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands.LoginDataCommands.getTokenDataFromTableLoginData;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.setGif;


public class GifFragmentDialogParser {

    private RetrofitClient retrofitClient = new RetrofitClient();
    private Response<GifDTO> getGif;

    public void loadGIF(final Context context, final ImageView imageViewGiff){
        //TODO add loidig indecator


        String token = getTokenDataFromTableLoginData(context);
        retrofitClient.serverApi.getGif(token).enqueue(new Callback<GifDTO>() {
            @Override
            public void onResponse(Call<GifDTO> call, Response<GifDTO> response) {
                getGif = response;
                String imageURI = response.body().getGifUrlPath();
                Log.d(LOG_TAG, "imageURIGiff: " + imageURI);

                setGif(context,imageURI,imageViewGiff);

                //TODO проверка Response

            }

            @Override
            public void onFailure(Call<GifDTO> call, Throwable t) {

            }
        });


    }


}
