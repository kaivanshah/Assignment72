package com.kaivanshah.assignment72;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaivanrasiklal.s on 17-03-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ProductDB";

    // Contacts table name
    private static final String TABLE_PRODUCT = "Products";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        // Create tables again
        onCreate(db);
    }


    // Adding new product
    void addProduct(String name, int price) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Product Name
        values.put(KEY_PRICE, price); // Product price

        // Inserting Row
        db.insert(TABLE_PRODUCT, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Contacts
    public ArrayList<String> getAllProducts() {
        ArrayList<String> productList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_NAME + " FROM " + TABLE_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String strProduct = cursor.getString(0);
                productList.add(strProduct);
            } while (cursor.moveToNext());
        }

        // return product list
        return productList;
    }
}
