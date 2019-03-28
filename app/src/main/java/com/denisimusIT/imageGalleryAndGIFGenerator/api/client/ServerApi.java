package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.GetAllUserImagesDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.GifDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.UserDTO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
                                          @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("login")
    Call<UserDTO> login(@Field("email") String email, @Field("password") String password);

    //Image
    @GET("all")
    Call<GetAllUserImagesDTO> getAllUserImages(@Header("token") String token);

    @GET("gif")
    Call<GifDTO> getGif(@Header("token") String token);

    @Multipart
    @POST("image")
    Call<ResponseBody> addImage(@Header("token") String token,
                                            @Part MultipartBody.Part image,
                                            @Part("description") RequestBody description,
                                            @Part("hashtag") RequestBody hashtag,
                                            @Part("latitude") RequestBody latitude,
                                            @Part("longitude") RequestBody longitude);

}
