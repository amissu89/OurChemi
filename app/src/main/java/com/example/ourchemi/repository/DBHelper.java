package com.example.ourchemi.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ourchemi.CommonAPI;
import com.example.ourchemi.models.Person;

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

    public Person selectByName(SQLiteDatabase db, String name)
    {
        String[] projection = {
                User.UserEntry.COL_ID,
                User.UserEntry.COL_NAME,
                User.UserEntry.COL_MBTI,
                User.UserEntry.COL_DDI,
                User.UserEntry.COL_GAPJA,
                User.UserEntry.COL_ZODIAC,
                User.UserEntry.COL_BIRTHDAY,
                User.UserEntry.COL_LUNAR_BIRTHDAY
        };

        String selection = User.UserEntry.COL_NAME + " = ? ";
        String[] selectionArgs = {name};

        String sortOrder = User.UserEntry.COL_NAME + " DESC";

        Cursor cursor = db.query(User.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,null,sortOrder);

        Person p = new Person();
        p.setCompleteInfo(false);
        while(cursor.moveToNext()) {
            System.out.println("name : " +  name);
            p.setName(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_NAME)));
            p.setMbti(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_MBTI)));
            p.setDdi(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_DDI)));
            p.setGapja(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_GAPJA)));
            p.setZodiacSign(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_ZODIAC)));

            String dateStr = cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_BIRTHDAY));
            System.out.println("datestr["+dateStr+"]");
            p.setBirthday(CommonAPI.getDateObjFromEditText(dateStr));
            dateStr = cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COL_LUNAR_BIRTHDAY));
            p.setLunarBirthday(CommonAPI.getDateObjFromEditText(dateStr));
            p.setCompleteInfo(true);

        }


        return p;
    }

    public long insertColumn(SQLiteDatabase sqLiteDatabase,
                             String name,
                             Person p)
    {
        ContentValues value = new ContentValues();
        value.put(User.UserEntry.COL_NAME,  name);
        value.put(User.UserEntry.COL_MBTI,  p.getMbti());
        value.put(User.UserEntry.COL_DDI,   p.getDdi());
        value.put(User.UserEntry.COL_GAPJA, p.getGapja());
        value.put(User.UserEntry.COL_ZODIAC,p.getZodiacSign());
        value.put(User.UserEntry.COL_BIRTHDAY, p.getBirthday().toString());
        value.put(User.UserEntry.COL_LUNAR_BIRTHDAY, p.getLunarBirthday().toString());

        return sqLiteDatabase.insert(User.UserEntry.TABLE_NAME, null, value);
    }

    public long updateColumnByName(SQLiteDatabase db, String name, Person p)
    {
        String where = User.UserEntry.COL_NAME + " = ? ";
        ContentValues value = new ContentValues();
        value.put(User.UserEntry.COL_NAME,  name);
        value.put(User.UserEntry.COL_BIRTHDAY, p.getBirthday().toString());
        value.put(User.UserEntry.COL_LUNAR_BIRTHDAY, p.getLunarBirthday().toString());
        value.put(User.UserEntry.COL_MBTI,  p.getMbti());
        value.put(User.UserEntry.COL_DDI,   p.getDdi());
        value.put(User.UserEntry.COL_GAPJA, p.getGapja());
        value.put(User.UserEntry.COL_ZODIAC,p.getZodiacSign());
        return db.update(User.UserEntry.TABLE_NAME, value, where, new String[] {name});
    }

    public Cursor getAllData(SQLiteDatabase db){
        return db.rawQuery("select * from " + User.UserEntry.TABLE_NAME, null);
    }

    public boolean isExistByName(SQLiteDatabase db, String name){
        String[] projection = {
                User.UserEntry.COL_ID,
                User.UserEntry.COL_NAME,
        };

        String selection = User.UserEntry.COL_NAME + " = ? ";
        String[] selectionArgs = {name};

        Cursor cursor = db.query(User.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,null,null);

        boolean isExist = false;
        while(cursor.moveToNext()) {
            isExist = true;
        }

        return isExist;
    }
}
