package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.provider.BaseColumns;

public class ImageGalleryAndGIFGeneratorContract {

    public static final class LoginUserInfo {
        public final static String TABLE_NAME = "loginUserInfo";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TOKEN = "token";
        public final static String COLUMN_AVATAR = "avatar";
        public final static String COLUMN_CREATION_TIME = "creationTime";
    }


    public static final class ImageData {
        public final static String TABLE_NAME = "imageData";
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_IMAGES_RESPONSE = "imageResponse";
    }
}
