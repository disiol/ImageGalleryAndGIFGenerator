package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import java.io.File;
import java.lang.ref.Reference;
import java.util.LinkedHashSet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//Authorization
public interface ServerApi {
    @POST("create")
    void createNewUser(@Query("username") String username, @Query("email") String email,
                       @Query("password") String password, @Query("avatar") File avatar);

    @POST("login")
    Reference login(@Query("email") String email, @Query("password") String password);

    //Image
    @GET("all")
    Call<LinkedHashSet<String>> getAllUserImages();

    @GET("gif")
    Call <String> getGif();

    @POST("create")
    String addImage(File image, String description, String hashtag, float latitude, float longitude);

}
