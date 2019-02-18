package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.provider.BaseColumns;

public class ImageGalleryAndGIFGeneratorContract {

    public static final class LoginData {
        public final static String TABLE_NAME = "loginUserInfo";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TOKEN = "token";
        public final static String COLUMN_AVATAR = "avatar";
        public final static String COLUMN_CREATION_TIME = "creationTime";
    }
}
