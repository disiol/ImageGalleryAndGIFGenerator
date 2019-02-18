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
        public final static String COLUMN_ID = "id";
        public final static String COLUMN_LONGITUDE = "longitude";
        public final static String COLUMN_ADDRESS = "address";
        public final static String COLUMN_WEATHER = "weather";
        public final static String COLUMN_LATITUDE = "latitude";
        public final static String COLUMN_SMALL_IMAGE_URL_PATH = "smallImageUrlPath";
        public final static String BIG_IMAGE_URL_PATH = "bigImageUrlPath";
    }
}
