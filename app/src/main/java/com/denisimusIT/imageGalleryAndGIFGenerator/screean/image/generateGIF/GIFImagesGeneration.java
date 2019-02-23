package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.generateGIF;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

public class GIFImagesGeneration extends AppCompatActivity {
    ImageView imageViewGiff;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifimages_generation);
        imageViewGiff = findViewById(R.id.iv_gif_animation);
        progressBar = findViewById(R.id.progressBar_gif);
        GIFImagesGenerationParser gifImagesGenerationParser = new GIFImagesGenerationParser();

        gifImagesGenerationParser.loadGIF(this,imageViewGiff,progressBar);
    }
}
