package com.example.ourchemi.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="OurChemistry.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(User.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(User.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    public long insertColumn(SQLiteDatabase sqLiteDatabase,
                             String name,
                             String mbti, String ddi, String gapja, String zodiac)
    {
        ContentValues value = new ContentValues();
        value.put(User.UserEntry.COL_NAME,  name);
        value.put(User.UserEntry.COL_MBTI,  mbti);
        value.put(User.UserEntry.COL_DDI,   ddi);
        value.put(User.UserEntry.COL_GAPJA, gapja);
        value.put(User.UserEntry.COL_ZODIAC,zodiac);

        return sqLiteDatabase.insert(User.UserEntry.TABLE_NAME, null, value);
    }

}
