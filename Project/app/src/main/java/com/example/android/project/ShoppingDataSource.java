package com.example.android.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Joe Bonifacio on 9/03/2017.
 */

public class ShoppingDataSource {
    private static final String TAG = "ShoppingDataSource";

    private SQLiteDatabase database;
    private ShoppingSQLiteOpenHelper dbHelper;

    private static final String[] TABLE_COLUMNS = {
            ShoppingSQLiteOpenHelper.COLUMN_ID,
            ShoppingSQLiteOpenHelper.COLUMN_NAME,
            ShoppingSQLiteOpenHelper.COLUMN_QUANTITY,
            ShoppingSQLiteOpenHelper.COLUMN_ISPURCHASED
    };

    public ShoppingDataSource(Context context){
        this.dbHelper = new ShoppingSQLiteOpenHelper(context);
    }

    public void open(){
        try{
            this.database = dbHelper.getWritableDatabase();
            Log.d(TAG, "Successfully opened the database!");
        }catch(SQLException sqle){
            Log.d(TAG, "Could not open the database! :: " + sqle.getMessage());
        }
    }

    public void close() {
        database.close();
    }

    public long insert(String name, int quantity, int isPurchased ){
        ContentValues values = new ContentValues();
        values.put(ShoppingSQLiteOpenHelper.COLUMN_NAME, name);
        values.put(ShoppingSQLiteOpenHelper.COLUMN_QUANTITY, quantity);
        values.put(ShoppingSQLiteOpenHelper.COLUMN_ISPURCHASED, isPurchased);
        long id = database.insert(ShoppingSQLiteOpenHelper.TABLE_SHOPPING, null, values);

        Log.d(TAG, "Inserted Item " + id + " into database!");
        return id;
    }
    public void Update(ShoppingItem item){
        int updatedPurchase;
        int isPurchased = item.getIsPurchased();

        ContentValues values = new ContentValues();
        if(isPurchased == 0){
            updatedPurchase = 1;

        }else{
            updatedPurchase = 0;
        }
        values.put(ShoppingSQLiteOpenHelper.COLUMN_ISPURCHASED, updatedPurchase);
        database.update(ShoppingSQLiteOpenHelper.TABLE_SHOPPING,
                values,
                "id=" + item.getId(),
                null);
        Log.d(TAG, "Item " + item.getId() + " is updated!!");

    }



    public void delete(ShoppingItem item){
        long id = item.getId();

        database.delete(
                ShoppingSQLiteOpenHelper.TABLE_SHOPPING,
                ShoppingSQLiteOpenHelper.COLUMN_ID + " = " + id,
                null
        );

        Log.d(TAG, "Item " + id + " deleted from database!");
    }

    public void deleteAll(){
        database.delete(ShoppingSQLiteOpenHelper.TABLE_SHOPPING, null, null);
    }

    public ArrayList<ShoppingItem> getAll(){

        ArrayList<ShoppingItem> items = new ArrayList<>();
        Cursor cursor = database.query(
                ShoppingSQLiteOpenHelper.TABLE_SHOPPING,
                TABLE_COLUMNS,
                null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ShoppingItem item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    private ShoppingItem cursorToItem(Cursor cursor){
        ShoppingItem temp = new ShoppingItem(cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
        return temp;
    }
}
