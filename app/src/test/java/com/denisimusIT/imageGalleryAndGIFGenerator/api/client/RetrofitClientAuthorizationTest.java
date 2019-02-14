package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.UserDTO;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getFile;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.RecvestsParser.*;
import static org.junit.Assert.assertEquals;

public class RetrofitClientAuthorizationTest {
    RetrofitClient retrofitClient = new RetrofitClient();

    @Test
    public void CrateNewUserErrorEmptyFieldsTest() throws IOException {

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

        RequestBody username = parseStringIntoRequestBody("Denis");
        RequestBody email = parseStringIntoRequestBody("disol@mail.ru");
        RequestBody password = parseStringIntoRequestBody("");
        MultipartBody.Part avatar = null;

        String expected = "{\"children\":{\"username\":{},\"email\":{}," +
                "\"password\":{\"errors\":[\"This value should not be blank.\"]}," +
                "\"avatar\":{\"errors\":[\"Please, upload the profile avatar picture.\"]}}}";

        Response<Response<UserDTO>> createNewUserDTOResponse = retrofitClient.serverApi.createNewUser(username, email, password, avatar).execute();
        String actual = createNewUserDTOResponse.errorBody().string();
        assertEquals("CrateNewUserErrorEmptyFields_password_avatar_Test", expected, actual);
    }


    @Test
    public void CrateNewUserError_This_value_is_already_used_Test() throws IOException {


        RequestBody username = parseStringIntoRequestBody("Denis");
        RequestBody email = parseStringIntoRequestBody("disiol@mail.ru");
        RequestBody password = parseStringIntoRequestBody("password");


        String path = "/home/denis/IT/AndroidStudioProjects/Portfolio/toMarcet/ImageGalleryAndGIFGenerator/app/src/test/java/com/denisimusIT/imageGalleryAndGIFGenerator";
        File file = new File(path, "u2.jpg");
        MultipartBody.Part avatar = MultipartBody.Part.create(getImageRequestBody(file));

        String expected = "{\"children\":{\"username\":{},\"email\":{\"errors\":[\"This value is already used.\"]},\"password\":{},\"avatar\":{}}}";

        Response<Response<UserDTO>> createNewUserDTOResponse = retrofitClient.serverApi.createNewUser(username, email, password, avatar).execute();
        String actual = createNewUserDTOResponse.errorBody().string();
        assertEquals("This value is already used", expected, actual);
    }


    @Test
    public void Login_Incorrect_email_or_password_test() throws IOException {

        String email = "denisimus_games@ukr.ne";
        String password = "password";

        String expected = "{\"error\":\"Incorrect email or password\"}";

        Call<UserDTO> login = retrofitClient.serverApi.login(email, password);

        String actual = login.execute().errorBody().string();
        assertEquals("Login_Incorrect_email_or_password_test", expected, actual);


    }

    @Test
    public void Login_Ok_test() throws IOException {

        String expected = "UserDTO{creationTime='2019-02-02 17:30:04', " +
                "avatarImageLink='http://api.doitserver.in.ua/upload/avatars/ff6f2733c9529ea7c7f752d5813d61a7.jpeg', " +
                "token='7fb2235a56e9d2da72e3bb0be7743689'}";

        String email = "denisimus_games@ukr.net";
        String password = "password";
        Call<UserDTO> login = retrofitClient.serverApi.login(email, password);

        String actual = login.execute().body().toString();
        assertEquals("Login_Ok_test", expected, actual);


    }


}