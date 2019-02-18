package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class ImageGalleryAndGIFGeneratorDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "imageGalleryAndGIFGenerator.db";


    private static final int DATABASE_VERSION = 3;

    public ImageGalleryAndGIFGeneratorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, " --- ImageGalleryAndGIFGeneratorDbHelper :  onCreate database ---");

        String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " + ImageGalleryAndGIFGeneratorContract.LoginData.TABLE_NAME + " ("
                + ImageGalleryAndGIFGeneratorContract.LoginData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ImageGalleryAndGIFGeneratorContract.LoginData.COLUMN_AVATAR + " TEXT NOT NULL, "
                + ImageGalleryAndGIFGeneratorContract.LoginData.COLUMN_CREATION_TIME + " TEXT NOT NULL, "
                + ImageGalleryAndGIFGeneratorContract.LoginData.COLUMN_TOKEN + " TEXT NOT NULL  " + ");";


        db.execSQL(SQL_CREATE_LOGIN_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "We are updated from the version " + oldVersion + " on the version " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + ImageGalleryAndGIFGeneratorContract.LoginData.TABLE_NAME );
        // Создаём новую таблицу
        onCreate(db);

    }
}
