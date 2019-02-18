package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class DatabaseComands {
    private static ImageGalleryAndGIFGeneratorDbHelper imageGalleryAndGIFGeneratorDbHelper;

    public final static void crateDataBase(Context context) {
        Log.d(LOG_TAG, "--- crateDataBase: ---");
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(context);
        SQLiteDatabase db = imageGalleryAndGIFGeneratorDbHelper.getReadableDatabase();
        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static void addDataToTableLoginData(Context login, String avatar, String creationTime, String token) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- addDataToTableLoginData ---");

        SQLiteDatabase db = connectToDB(login);

        Log.d(LOG_TAG, "--- Insert in table LoginData  ---");

        // подготовим данные для вставки в виде пар: наименование столбца - значение

        cv.put(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_AVATAR, avatar);
        cv.put(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_CREATION_TIME, creationTime);
        cv.put(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_TOKEN, token);
        long rowID = db.insert(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME, null, cv);
        Log.d(LOG_TAG, "row inserted,ok");

        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static void getAllDataFromTableLoginData(Context context) {
        SQLiteDatabase db = connectToDB(context);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo._ID);
            int avatarColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_AVATAR);
            int creationTimeColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_CREATION_TIME);
            int tokenColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_TOKEN);

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", avatar = " + c.getString(avatarColIndex) +
                                ", creationTime = " + c.getString(creationTimeColIndex) +
                                " token = " + c.getString(tokenColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        imageGalleryAndGIFGeneratorDbHelper.close();
    }


    public final static String getAvatarDataFromTableLoginData(Context context) {
        SQLiteDatabase db = connectToDB(context);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo._ID);
            int avatarColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_AVATAR);
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                String avatar = c.getString(avatarColIndex);
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", avatar = " + avatar);
                return avatar;

            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        ;
        imageGalleryAndGIFGeneratorDbHelper.close();
        return null;
    }
    public final static String getCreationTimeDataFromTableLoginData(Context context) {
        SQLiteDatabase db = connectToDB(context);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo._ID);
            int creationTimeColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_CREATION_TIME);
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                String avatar = c.getString(creationTimeColIndex);
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", creationTime = " + avatar);
                return avatar;

            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        ;
        imageGalleryAndGIFGeneratorDbHelper.close();
        return null;
    }

    public final static String getTokenDataFromTableLoginData(Context context) {
        SQLiteDatabase db = connectToDB(context);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo._ID);
            int columnIndex = c.getColumnIndex(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.COLUMN_TOKEN);
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                String token = c.getString(columnIndex);
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", token = " + token);
                return token;

            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        ;
        imageGalleryAndGIFGeneratorDbHelper.close();
        return null;
    }

    public static void clearTableLoginData(Context context) {
        Log.d(LOG_TAG, "--- Clear " + ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME + "---");
        SQLiteDatabase db = connectToDB(context);
        // удаляем все записи
        int clearCount = db.delete(ImageGalleryAndGIFGeneratorContract.LoginUserInfo.TABLE_NAME, null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);
    }

    private static SQLiteDatabase connectToDB(Context context) {
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(context);
        return imageGalleryAndGIFGeneratorDbHelper.getWritableDatabase();
    }


}
