package com.denisimusIT.imageGalleryAndGIFGenerator.util;

import java.io.File;
import java.net.MalformedURLException;

public class FileUtils {

    private static String FilePath;

    public static String getFile(String inputFilePath) {
        try {
            FilePath = new File(String.valueOf(inputFilePath)).toURI().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return FilePath;
    }
}