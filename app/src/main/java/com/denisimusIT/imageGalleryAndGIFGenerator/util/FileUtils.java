package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

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

    public final static void getImageForAvatar(Uri imageURI, ImageView imageViewAvatar) {
        Picasso.get().load(imageURI).into(imageViewAvatar);
        Log.d(LOG_TAG, "FileUtils getImageForAvatar: " + imageURI);
    }

}