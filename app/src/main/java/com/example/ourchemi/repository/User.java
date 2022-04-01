package com.example.ourchemi.repository;

import android.provider.BaseColumns;

public final class User {
    private User(){};

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME="user";
        public static final String COL_ID="id";
        public static final String COL_NAME="name";
        public static final String COL_MBTI="mbti";
        public static final String COL_DDI="ddi";
        public static final String COL_GAPJA="gapja";
        public static final String COL_ZODIAC="zodiac";
    }
    public static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE " + UserEntry.TABLE_NAME +" (" +
                    UserEntry.COL_ID + " INTEGER PRIMARY KEY, " +
                    UserEntry.COL_NAME + " TEXT, " +
                    UserEntry.COL_MBTI + " TEXT, " +
                    UserEntry.COL_DDI + " TEXT, " +
                    UserEntry.COL_GAPJA + " TEXT, " +
                    UserEntry.COL_ZODIAC + " TEXT )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
}
