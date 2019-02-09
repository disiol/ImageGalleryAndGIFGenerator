package com.denisimusIT.imageGalleryAndGIFGenerator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class DatabaseComands {
    private static ImageGalleryAndGIFGeneratorDbHelper imageGalleryAndGIFGeneratorDbHelper;

    public final static void crateDataBase(Context login) {
        Log.d(LOG_TAG, "--- crateDataBase: ---");
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(login);
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

        cv.put(LoginContract.LoginData.COLUMN_AVATAR, avatar);
        cv.put(LoginContract.LoginData.COLUMN_CREATION_TIME, creationTime);
        cv.put(LoginContract.LoginData.COLUMN_TOKEN, token);
        long rowID = db.insert(LoginContract.LoginData.TABLE_NAME, null, cv);
        Log.d(LOG_TAG, "row inserted,ok");

        imageGalleryAndGIFGeneratorDbHelper.close();
    }

    public final static void getAllDataFromTableLoginData(Context login) {
        SQLiteDatabase db = connectToDB(login);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(LoginContract.LoginData.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(LoginContract.LoginData._ID);
            int avatarColIndex = c.getColumnIndex(LoginContract.LoginData.COLUMN_AVATAR);
            int creationTimeColIndex = c.getColumnIndex(LoginContract.LoginData.COLUMN_CREATION_TIME);
            int tokenColIndex = c.getColumnIndex(LoginContract.LoginData.COLUMN_TOKEN);

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


    public final static String getAvatarDataFromTableLoginData(Context login) {
        SQLiteDatabase db = connectToDB(login);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(LoginContract.LoginData.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(LoginContract.LoginData._ID);
            int avatarColIndex = c.getColumnIndex(LoginContract.LoginData.COLUMN_AVATAR);
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
    public final static String getCreationTimeDataFromTableLoginData(Context login) {
        SQLiteDatabase db = connectToDB(login);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(LoginContract.LoginData.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(LoginContract.LoginData._ID);
            int creationTimeColIndex = c.getColumnIndex(LoginContract.LoginData.COLUMN_CREATION_TIME);
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

    public final static String getTokenDataFromTableLoginData(Context login) {
        SQLiteDatabase db = connectToDB(login);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query(LoginContract.LoginData.TABLE_NAME, null, null, null, null, null, null);


        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex(LoginContract.LoginData._ID);
            int columnIndex = c.getColumnIndex(LoginContract.LoginData.COLUMN_TOKEN);
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

    public static void clearTable(Context login) {
        Log.d(LOG_TAG, "--- Clear " + LoginContract.LoginData.TABLE_NAME + "---");
        SQLiteDatabase db = connectToDB(login);
        // удаляем все записи
        int clearCount = db.delete(LoginContract.LoginData.TABLE_NAME, null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);
    }

    private static SQLiteDatabase connectToDB(Context login) {
        imageGalleryAndGIFGeneratorDbHelper = new ImageGalleryAndGIFGeneratorDbHelper(login);
        return imageGalleryAndGIFGeneratorDbHelper.getWritableDatabase();
    }


}
