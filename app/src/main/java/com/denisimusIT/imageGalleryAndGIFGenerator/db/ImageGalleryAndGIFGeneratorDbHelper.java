package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class ImageGalleryAndGIFGeneratorDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "imageGalleryAndGIFGenerator.db";


    private static final int DATABASE_VERSION = 1;

    public ImageGalleryAndGIFGeneratorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, " --- ImageGalleryAndGIFGeneratorDbHelper :  onCreate database ---");

        String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " + LoginContract.LoginData.TABLE_NAME + " ("
                + LoginContract.LoginData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LoginContract.LoginData.COLUMN_AVATAR + " TEXT NOT NULL, "
                + LoginContract.LoginData.COLUMN_TOKEN + " TEXT NOT NULL  " + ");";


        db.execSQL(SQL_CREATE_LOGIN_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}