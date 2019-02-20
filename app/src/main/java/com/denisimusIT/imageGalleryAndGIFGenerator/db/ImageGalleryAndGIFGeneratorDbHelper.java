package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class ImageGalleryAndGIFGeneratorDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "imageGalleryAndGIFGenerator.db";


    private static final int DATABASE_VERSION = 11;

    public ImageGalleryAndGIFGeneratorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d(LOG_TAG, " --- ImageGalleryAndGIFGeneratorDbHelper :  onCreate database ---");

        crateLoginUserInfo(database);
        crateImageData(database);


    }

    public void crateLoginUserInfo(SQLiteDatabase database) {
        String SQL_CREATE_LOGIN_USER_INFO_TABLE = "CREATE TABLE " + ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME + " ("
                + ImageGalleryAndGIFGeneratorContract.LoginUserInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_AVATAR + " TEXT NOT NULL, "
                + ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_CREATION_TIME + " TEXT NOT NULL, "
                + ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_TOKEN + " TEXT NOT NULL  " + ");";


        database.execSQL(SQL_CREATE_LOGIN_USER_INFO_TABLE);
    }

    public void crateImageData(SQLiteDatabase database) {
        String SQL_CREATE_IMAGE_DATA = "CREATE TABLE " + ImageGalleryAndGIFGeneratorContract.ImageData.TABLE_NAME + " ("
                + ImageGalleryAndGIFGeneratorContract.ImageData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_IMAGES_RESPONSE + " TEXT NOT NULL  " + ");";


        database.execSQL(SQL_CREATE_IMAGE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w("SQLite", "We are updated from the version " + oldVersion + " on the version " + newVersion);

        // Удаляем старую таблицу и создаём новую
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  ImageGalleryAndGIFGeneratorContract.ImageData.TABLE_NAME);
        // Создаём новую таблицу
        onCreate(sqLiteDatabase);  // Удаляем старую таблицу и создаём новую



    }



    public void dropAndCrateTable(SQLiteDatabase sqLiteDatabase, String tableName) {
        // Удаляем старую таблицу и создаём новую
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        // Создаём новую таблицу
        onCreate(sqLiteDatabase);  // Удаляем старую таблицу и создаём новую
    }
}
