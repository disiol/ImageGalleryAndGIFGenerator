package com.denisimusIT.imageGalleryAndGIFGenerator.screean.signIn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.Image;

import java.io.IOException;
import java.util.LinkedHashSet;

import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //TODO проверку на зполненость обязательніх полей и вод ошибок
//        try {
//            Response<LinkedHashSet<String>> getAddedImages = new Image().getAddedImages();
//            Log.d("My_log", String.valueOf(getAddedImages));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
