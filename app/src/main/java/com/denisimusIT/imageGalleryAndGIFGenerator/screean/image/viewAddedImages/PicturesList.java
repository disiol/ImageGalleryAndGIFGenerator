package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

import java.io.IOException;

public class PicturesList extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_list);
        PucturesListParser pucturesListParser = new PucturesListParser();
        try {
            recyclerView = findViewById(R.id.recycler_view);
            pucturesListParser.loadAllImages(this, recyclerView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
