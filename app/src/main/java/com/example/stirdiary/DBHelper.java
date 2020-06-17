package com.example.stirdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jne
 * Date: 2015/1/6.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "APP.db";
    public static final String TABLE_NAME = "diary";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table Orders(Id integer primary key, CustomName text, OrderPrice integer, Country text);
        String sql = "create table if not exists diary("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "diaryid TEXT,"
                + "title TEXT,"
                + "content TEXT,"
                + "bottle INTEGER,"
                + "share INTEGER,"
                + "stir INTEGER,"
                + "date TEXT,"
                + "sentiment INTEGER"
                + ")";
        sqLiteDatabase.execSQL(sql);
        System.out.println("DB created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}