package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;

import org.junit.Test;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class RetrofitClientAuthorizationTest {

    @Test
    public void CrateNewUserErrorEmptyFieldsTest() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();

        RequestBody username = parseStringIntoRequestBody("");
        RequestBody email = parseStringIntoRequestBody("");
        RequestBody password = parseStringIntoRequestBody("");
        MultipartBody.Part avatar = null;

        String expected = "{\"children\":{\"username\":{}," +
                "\"email\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"password\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"avatar\":{\"errors\":[\"Please, upload the profile avatar picture.\"]}}}";
        Response<Response<UserDTO>> createNewUserDTOResponse = retrofitClient.serverApi.createNewUser(username, email, password, avatar).execute();
        String actual = createNewUserDTOResponse.errorBody().string();
        assertEquals("CrateNewUserErrorEmptyFields", expected, actual);
    }

    @Test
    public void CrateNewUserErrorEmptyFields_Email_password_avatar_Test() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();


        RequestBody username = parseStringIntoRequestBody("Denis");
        RequestBody email = parseStringIntoRequestBody("");
        RequestBody password = parseStringIntoRequestBody("");
        MultipartBody.Part avatar = null;

        String expected = "{\"children\":{\"username\":{}," +
                "\"email\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"password\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"avatar\":{\"errors\":[\"Please, upload the profile avatar picture.\"]}}}";
        Response<Response<UserDTO>> createNewUserDTOResponse = retrofitClient.serverApi.createNewUser(username, email, password, avatar).execute();
        String actual = createNewUserDTOResponse.errorBody().string();
        assertEquals("CrateNewUserErrorEmptyFields_Email_password_avatar_Test", expected, actual);
    }

    @Test
    public void CrateNewUserErrorEmptyFields_password_avatar_Test() throws IOException {
        RetrofitClient retrofitClient = new RetrofitClient();

        RequestBody username = parseStringIntoRequestBody("Denis");
        RequestBody email = parseStringIntoRequestBody("disol@mail.ru");
        RequestBody password = parseStringIntoRequestBody("");
        MultipartBody.Part avatar = null;

        String expected = "{\"children\":{\"username\":{},\"email\":{}," +
                "\"password\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"avatar\":{\"errors\":[\"Please, upload the profile avatar picture.\"]}}}";

        Response<Response<UserDTO>> createNewUserDTOResponse = retrofitClient.serverApi.createNewUser(username, email, password, avatar).execute();
        System.err.println("createNewUserDTOResponse: " + createNewUserDTOResponse.toString());
        String actual = createNewUserDTOResponse.errorBody().string();
        assertEquals("CrateNewUserErrorEmptyFields_password_avatar_Test", expected, actual);
    }

    public static RequestBody parseStringIntoRequestBody(String string) {
        return RequestBody.create(MediaType.parse("text/plain"), string);
    }
}