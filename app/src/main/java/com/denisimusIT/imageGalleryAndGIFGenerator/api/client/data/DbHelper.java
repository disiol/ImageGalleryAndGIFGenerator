package com.denisimusIT.imageGalleryAndGIFGenerator.api.client.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ImageGalleryAndGIFGenerator.db";


    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " + Contract.LoginData.TABLE_NAME + " ("
                + Contract.LoginData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.LoginData.COLUMN_TOCEN + " TEXT , "
                + Contract.LoginData.COLUMN_AVATAR + " TEXT );";

        db.execSQL(SQL_CREATE_LOGIN_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
