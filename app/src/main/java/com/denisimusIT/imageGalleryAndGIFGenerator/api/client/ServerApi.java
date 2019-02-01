package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import java.io.File;
import java.util.LinkedHashSet;

import retrofit2.http.GET;
import retrofit2.http.POST;

//Authorization
public interface ServerApi {
    @POST("http://api.doitserver.in.ua/create")
    void createNewUser(String username, String email, String password, File avatar);

    @POST("http://api.doitserver.in.ua/login")
    void login(String email, String password);

    //Image
    @GET("http://api.doitserver.in.ua/all")
    LinkedHashSet<String> getAllUserImages();

    @GET("http://api.doitserver.in.ua/gif")
    String getGif();

    @POST("http://api.doitserver.in.ua/create")
    void addImage(File image, String description, String hashtag, float latitude, float longitude);

}
