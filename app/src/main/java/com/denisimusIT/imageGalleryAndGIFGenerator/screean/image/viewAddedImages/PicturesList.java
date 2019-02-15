package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

import java.io.IOException;

public class PicturesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_list);
        PucturesListParser pucturesListParser = new PucturesListParser();
        try {
            pucturesListParser.loadAllImages(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
