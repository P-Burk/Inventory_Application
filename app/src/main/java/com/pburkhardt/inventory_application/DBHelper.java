package com.pburkhardt.inventory_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "ID";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";

    //CONSTRUCTOR
    public DBHelper(@Nullable Context context) {
        super(context, "InventoryApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTableString = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT)";

        sqLiteDatabase.execSQL(createUserTableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Add a user to the database
    public boolean addUser(InventoryUser newUser) {
        SQLiteDatabase invAppDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USER_NAME, newUser.getInvUserName());
        contentValues.put(COLUMN_USER_PASSWORD, newUser.getInvUserPassword());

        long insert = invAppDB.insert(USER_TABLE, null, contentValues);
        return insert != -1;  //true
    }
}
