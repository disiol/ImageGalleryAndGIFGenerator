package com.denisimusIT.imageGalleryAndGIFGenerator.api.client.data;

import android.provider.BaseColumns;

public final class Contract {
    public Contract() {
    }

    public static final class LoginData {
        public final static String TABLE_NAME = "login user info";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TOCEN= "token";
        public final static String COLUMN_AVATAR = "avatar" ;
    }
}
