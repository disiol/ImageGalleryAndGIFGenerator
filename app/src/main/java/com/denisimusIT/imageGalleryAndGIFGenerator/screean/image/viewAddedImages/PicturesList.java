package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.addNewImage.UploadNewPicture;
import com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.generateGIF.GifFragmentDialog;

public class PicturesList extends AppCompatActivity {
    private RecyclerView recyclerView;

    private GifFragmentDialog gifFragmentDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_list);
        PucturesListParser pucturesListParser = new PucturesListParser();
        gifFragmentDialog = GifFragmentDialog.newInstance();
        recyclerView = findViewById(R.id.recycler_view);
        pucturesListParser.loadAllImages(this, recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bt_play_gif:
                gifFragmentDialog.show(getSupportFragmentManager().beginTransaction(), "GifFragmentDialog");
                return true;

            case R.id.bt_add_image:
                startActivityForResult(new Intent(PicturesList.this, UploadNewPicture.class), 0);
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
