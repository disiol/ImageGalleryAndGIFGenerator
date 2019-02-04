package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GetAllUserImages;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.GifDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

//Authorization
public interface ServerApi {
    @Multipart
    @POST("create")
    Call<Response<UserDTO>> createNewUser(@Part("username") RequestBody userName,
                                          @Part("email") RequestBody email,
                                          @Part("password") RequestBody password,
                                          @Part MultipartBody.Part  file);
    @FormUrlEncoded
    @POST("login")
    Call<UserDTO> login(@Field("email") String email, @Field("password") String password);

    //Image
    @GET("all")
    Call<GetAllUserImages> getAllUserImages(@Header("token") String token);

    @GET("gif")
    Call<GifDTO> getGif(@Header("token") String token);

    @Multipart
    @POST("image")
        //TODO
    Call<String> addImage(@Header("token") String token, @Part("image") File image, @Part("description") String description,
                          @Part("hashtag") String hashtag, @Part("latitude") float latitude,
                          @Part("longitude") float longitude);

}
