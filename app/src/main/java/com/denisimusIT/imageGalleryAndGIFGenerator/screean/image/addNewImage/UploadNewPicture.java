package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.addNewImage;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.IMAGE_MEDIA_TYPE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.SELECT_PICTURE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImageForAvatar;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.setImage;

public class UploadNewPicture extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageViewUploadImage;
    private ProgressBar progressBarUploadimage;
    private EditText editTextDescription;
    private EditText editTextHashTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_picture);

        imageViewUploadImage = findViewById(R.id.iv_upload_image);
        progressBarUploadimage = findViewById(R.id.pb_upload_image);
        editTextDescription = findViewById(R.id.et_description);
        editTextHashTag = findViewById(R.id.et_hash_tag);

        imageViewUploadImage.setOnClickListener(this);
        //TODO проверку на зполненость обязательніх полей и вод ошибок

    }

    @Override
    public void onClick(View v) {
       selectImage();
    }


    private void selectImage() {
        //TODO refactoring
        //TODO add select avatar frm camera
        Intent intent = new Intent();
        intent.setType(IMAGE_MEDIA_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                setImage(String.valueOf(selectedImageUri), imageViewUploadImage);
                imageViewUploadImage.setTag(selectedImageUri);


            }
        }
    }
}
