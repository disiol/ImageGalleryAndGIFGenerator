package com.denisimusIT.imageGalleryAndGIFGenerator.api;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.App;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GetAllUserImages;

import java.io.IOException;
import java.util.LinkedHashSet;

import retrofit2.Call;
import retrofit2.Response;

public class Image {
   public Response<GetAllUserImages> getAddedImages(String token) throws IOException {
       //TODO

        Response<GetAllUserImages> actual = App.getApi().getAllUserImages(token).execute();

       return actual;
   }

    public  void addANewImage(){
        //TODO

    }

    public void generateGIF(){
        //TODO

    }
}
