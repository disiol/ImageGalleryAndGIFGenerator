package com.denisimusIT.imageGalleryAndGIFGenerator.db.DatabaseCommands;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denisimusIT.imageGalleryAndGIFGenerator.db.ImageGalleryAndGIFGeneratorContract;
import com.denisimusIT.imageGalleryAndGIFGenerator.db.ImageGalleryAndGIFGeneratorDbHelper;

import java.util.LinkedHashMap;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class ImageDataCommands {
    private static ImageGalleryAndGIFGeneratorDbHelper imageGalleryAndGIFGeneratorDbHelper;

    private static String columnImagesResponse = ImageGalleryAndGIFGeneratorContract.ImageData.COLUMN_IMAGES_RESPONSE;
    private static String tableName = ImageGalleryAndGIFGeneratorContract.ImageData.TABLE_NAME;
    private static String id;

    public final static void crateDataBase(Context context) {
        Log.d(LOG_TAG, "--- crateDataBase: ---");
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(context);
        SQLiteDatabase db = imageGalleryAndGIFGeneratorDbHelper.getReadableDatabase();
        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static void addDataToTableImageData(Context context, String imagesResponse) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- addDataToTableLoginData ---");

        SQLiteDatabase db = connectToDB(context);

        Log.d(LOG_TAG, "--- Insert in table LoginData  ---");

        // подготовим данные для вставки в виде пар: наименование столбца - значение
        cv.put(columnImagesResponse, imagesResponse);
        long rowID = db.insert(tableName, null, cv);
        Log.d(LOG_TAG, "row inserted,ok");

        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static LinkedHashMap<String, String> getAllDataFromTableImageData(Context context) {
        LinkedHashMap<String, String> linkedHashMapImage = new LinkedHashMap<>();

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
            int columnImagesResponseIndex = c.getColumnIndex(columnImagesResponse);

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(LOG_TAG, "--- getAllDataFromTableImageData ---" +
                        id + " = " + c.getInt(idColIndex) + "\n" +
                        columnImagesResponse + " = " + c.getString(columnImagesResponseIndex)
                );

//                linkedHashMapImage.put()


            } while (c.moveToNext());
        } else {
            Log.d(LOG_TAG, "0 rows");
        }
        c.close();
        imageGalleryAndGIFGeneratorDbHelper.close();

        return linkedHashMapImage;
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
