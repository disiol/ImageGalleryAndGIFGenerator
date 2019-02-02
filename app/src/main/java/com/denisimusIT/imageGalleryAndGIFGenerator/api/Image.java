package com.denisimusIT.imageGalleryAndGIFGenerator.api;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.App;

import java.io.IOException;
import java.util.LinkedHashSet;

import retrofit2.Call;
import retrofit2.Response;

public class Image {
   public Response<LinkedHashSet<String>> getAddedImages() throws IOException {
       //TODO

       LinkedHashSet <Call> LinkedHashSetAddedImages = new LinkedHashSet<>();
        Response<LinkedHashSet<String>> actual = App.getApi().getAllUserImages().execute();

       return actual;
   }

    public  void addANewImage(){
        //TODO

    }

    public void generateGIF(){
        //TODO

    }
}
