package com.example.android.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Joe Bonifacio on 9/03/2017.
 */

public class ShoppingSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "ShopSQLiteOpenHelper";

    //Database name and version
    public static final String DATABASE_NAME = "shopping.db";
    public static final int DATABASE_VERSION = 1;

    //shopping table details
    public static final String TABLE_SHOPPING = "shopping";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ISPURCHASED = "isPurchased";

    //create table
    private static final String TABLE_CREATE = "create table " + TABLE_SHOPPING + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_QUANTITY + " integer not null, "
            + COLUMN_ISPURCHASED + " integer not null);";

    public ShoppingSQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);
        onCreate(db);
        Log.d(TAG, "DATABASE UPGRADED");
    }
}
