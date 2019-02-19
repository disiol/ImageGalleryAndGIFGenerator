package com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.db.ImageGalleryAndGIFGeneratorContract;
import com.denisimusIT.imageGalleryAndGIFGenerator.db.ImageGalleryAndGIFGeneratorDbHelper;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class ImageDataCommands {
    private static ImageGalleryAndGIFGeneratorDbHelper imageGalleryAndGIFGeneratorDbHelper;

    private static String columnId = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_ID;
    private static String columnLongitude = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_LONGITUDE;
    private static String columnLatitude = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_LATITUDE;
    private static String columnAddress = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_ADDRESS;
    private static String columnWeather = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_WEATHER;
    private static String columnSmallImageUrlPath = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_SMALL_IMAGE_URL_PATH;
    private static String columnBigImageUrlPath = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_BIG_IMAGE_URL_PATH;
    private static String tableName = ImageGalleryAndGIFGeneratorContract.ImageData.TABLE_NAME;
    private static String id;

    public final static void crateDataBase(Context context) {
        Log.d(LOG_TAG, "--- crateDataBase: ---");
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(context);
        SQLiteDatabase db = imageGalleryAndGIFGeneratorDbHelper.getReadableDatabase();
        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static void addDataToTableImageData(Context context, String id, String longitude, String latitude, String address, String smallImageUrlPath, String bigImageUrlPath, String weather) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- addDataToTableLoginData ---");

        SQLiteDatabase db = connectToDB(context);

        Log.d(LOG_TAG, "--- Insert in table LoginData  ---");

        // подготовим данные для вставки в виде пар: наименование столбца - значение

        cv.put(columnId, id);
        cv.put(columnLongitude, longitude);
        cv.put(latitude, latitude);
        cv.put(columnAddress, address);
        cv.put(columnWeather, weather);
        cv.put(columnSmallImageUrlPath, smallImageUrlPath);
        cv.put(columnBigImageUrlPath, bigImageUrlPath);
        long rowID = db.insert(tableName, null, cv);
        Log.d(LOG_TAG, "row inserted,ok");

        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static void getAllDataFromTableLoginData(Context context) {
        SQLiteDatabase db = connectToDB(context);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(ImageGalleryAndGIFGeneratorContract.ImageData.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            id = ImageGalleryAndGIFGeneratorContract.ImageData._ID;

            int idColIndex = c.getColumnIndex(id);
            int columnIndexId = c.getColumnIndex(columnId);
            int columnLongitudeIndex = c.getColumnIndex(columnLongitude);
            int columnLatitudeIndex = c.getColumnIndex(columnLatitude);
            int columnAddressIndex = c.getColumnIndex(columnAddress);
            int columnWeatherIndex = c.getColumnIndex(columnWeather);
            int columnSmallImageUrlPathIndex = c.getColumnIndex(columnSmallImageUrlPath);
            int columnBigImageUrlPathIndex = c.getColumnIndex(columnBigImageUrlPath);

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(LOG_TAG, "--- getAllDataFromTableLoginData ---" +
                        id + " = " + c.getInt(idColIndex) + "\n" +
                        columnId + "=" + c.getString(columnIndexId) + "\n" +
                        columnLongitude + "=" + c.getString(columnLongitudeIndex) + "\n" +
                        columnLatitude + " = " + c.getString(columnLatitudeIndex) + "\n" +
                        columnAddress + " = " + c.getString(columnAddressIndex) + "\n" +
                        columnWeather + " = " + c.getString(columnWeatherIndex) + "\n" +
                        columnSmallImageUrlPath + " = " + c.getString(columnSmallImageUrlPathIndex) + "\n" +
                        columnBigImageUrlPath + " = " + c.getString(columnBigImageUrlPathIndex)
                );
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        imageGalleryAndGIFGeneratorDbHelper.close();
    }


    public static void clearTableLoginData(Context context) {
        Log.d(LOG_TAG, "--- Clear " + tableName + "---");
        SQLiteDatabase db = connectToDB(context);
        // удаляем все записи
        int clearCount = db.delete(tableName, null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);
    }

    private static SQLiteDatabase connectToDB(Context context) {
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(context);
        return imageGalleryAndGIFGeneratorDbHelper.getWritableDatabase();
    }


}
