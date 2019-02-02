package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import java.io.File;
import java.lang.ref.Reference;
import java.util.LinkedHashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//Authorization
public interface ServerApi {
    @POST("create")
        //TODO
    Call<String> createNewUser(@Query("username") String username, @Query("email") String email,
                               @Query("password") String password, @Query("avatar") File avatar);

    @POST("login")
    Call<String> login(@Query("email") String email, @Query("password") String password);

    //Image
    @GET("all")
    Call<LinkedHashSet<String>> getAllUserImages();

    @GET("gif")
    Call<String> getGif();

    @POST("image")
        //TODO
    Call<String> addImage(@Query("image") File image, @Query("description") String description,
                          @Query("hashtag") String hashtag, @Query("latitude") float latitude,
                          @Query("longitude") float longitude);

}
