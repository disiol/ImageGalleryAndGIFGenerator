package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.addNewImage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.FileGpsUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil.showToastError;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.IMAGE_MEDIA_TYPE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.SELECT_PICTURE;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.setImage;

public class UploadNewPicture extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private ImageView imageViewUploadImage;
    private ProgressBar progressBarUploadImage;
    private EditText editTextDescription;
    private EditText editTextHashTag;

    private GoogleApiClient googleApiClient;
    private UploadNewPictureParser uploadNewPictureParser;

    private String latitude;
    private String longitude;
    private Location lastLocation;
    private Uri selectedImageData;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_picture);

        googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();
        uploadNewPictureParser = new UploadNewPictureParser();

        imageViewUploadImage = findViewById(R.id.iv_upload_image);
        progressBarUploadImage = findViewById(R.id.pb_upload_image);
        editTextDescription = findViewById(R.id.et_description);
        editTextHashTag = findViewById(R.id.et_hash_tag);

        imageViewUploadImage.setOnClickListener(this);
        context = this;
    }

    @Override
    public void onClick(View v) {
        selectImage();
        context = v.getContext();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_uplod_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.bt_up_lod_image) {
            Log.d(LOG_TAG, "ad_image_view");

            disableInputs();
            getCoordinates();


            uploadNewPictureParser.addImage(imageViewUploadImage, progressBarUploadImage,
                    editTextDescription, editTextHashTag, latitude, longitude);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
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
                selectedImageData = data.getData();
                setImage(String.valueOf(selectedImageData), imageViewUploadImage);
                imageViewUploadImage.setTag(selectedImageData);


            }
        }
    }


    private void getCoordinates() {
        if (selectedImageData != null) {
            FileGpsUtil fileGpsUtil = new FileGpsUtil(selectedImageData.getPath());

            latitude = "0";
            longitude = "0";

            if (fileGpsUtil.getCoordinates() != null) {
                latitude = fileGpsUtil.getCoordinates().getStringLatitude();
                longitude = fileGpsUtil.getCoordinates().getStringLongitude();
            } else if (lastLocation != null) {
                latitude = String.valueOf(lastLocation.getLatitude());
                longitude = String.valueOf(lastLocation.getLongitude());
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void disableInputs() {
        imageViewUploadImage.setEnabled(false);
        editTextDescription.setEnabled(false);
        editTextHashTag.setEnabled(false);
        progressBarUploadImage.setVisibility(View.VISIBLE);
        showToastError(this, getString(R.string.upload_start));
    }

    public static Context getContext() {
        return context;
    }
}
