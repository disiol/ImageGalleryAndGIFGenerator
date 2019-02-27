package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.MalformedURLException;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class FileUtils {

    private static String FilePath;

    public final static String getFile(String inputFilePath) {
        try {
            FilePath = new File(String.valueOf(inputFilePath)).toURI().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return FilePath;
    }

    public final static void getImageForAvatar(Uri imageURI, ImageView imageView) {
        int radius = 0;
        Picasso.get()
                .load(imageURI)
                .transform(new CircularTransformation(radius))
                .into(imageView);
        Log.d(LOG_TAG, "FileUtils getImageForAvatar: " + imageURI);


    }

    public final static void getImage(Uri imageURI, ImageView imageView) {
        int radius = 0;
        Picasso.get()
                .load(imageURI)
                .centerCrop()
                .into(imageView);
        Log.d(LOG_TAG, "FileUtils getImageForAvatar: " + imageURI);


    }

    public final static void setImage(String imageURI, ImageView imageView) {
        int radius = 0;
        Picasso.get()
                .load(imageURI)
                .placeholder(R.drawable.default_image_background)
                .error(R.drawable.eror)//TODO eror pitche
                .fit()
                .centerCrop()
                .into(imageView);
        Log.d(LOG_TAG, "FileUtils setImage: " + imageURI);

    }

    public final static void setGif(Context context, String imageURI, ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(imageURI).error(R.drawable.gif_default)
                .centerCrop()
                .override(imageView.getWidth(), imageView.getHeight())
                .into(imageView);
        Log.d(LOG_TAG, "FileUtils setGif: " + imageURI);

    }


}