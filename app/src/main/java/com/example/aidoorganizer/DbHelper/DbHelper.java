package com.example.aidoorganizer.DbHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_NAME = "notes";
    private static final String COL1 = "note_id";
    private static final String COL2 = "notebooks";
    private static final String COL3 = "note";
    private static final String COL4 = "note_image";
//    private static final String COL5 = "note_audio";
    private static final String COL6 = "note_date";
    private static final String COL7 = "note_time";

    public DbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL2 + " TEXT,"
                + COL3 + " TEXT,"
                + COL4 + " BLOB,"
                + COL6 + " TEXT,"
                + COL7 + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
