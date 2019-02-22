package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.setImage;

public class BigPicherActivity extends AppCompatActivity {
    ImageView imageViewActivityBigImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        imageViewActivityBigImage = findViewById(R.id.iv_activity_big_image);

        Intent intent = getIntent();
        String imageUrlPath = intent.getStringExtra("bigImageUrlPath");
        Log.d(LOG_TAG, "imageUrlPath:  " + imageUrlPath);

        if (imageUrlPath != null) {
            setImage(imageUrlPath, imageViewActivityBigImage); //TODO
        }


    }


}
