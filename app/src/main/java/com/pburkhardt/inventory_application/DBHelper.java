package com.pburkhardt.inventory_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_ID = "ID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_PHONE_NUM = "USER_PHONE_NUM";
    public static final String COLUMN_USER_SMS_FLAG = "USER_SMS_FLAG";
    public static final String INVENTORY_TABLE = "INVENTORY_TABLE";
    public static final String COLUMN_ITEM_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_COUNT = "ITEM_COUNT";


    //CONSTRUCTOR
    public DBHelper(@Nullable Context context) {
        super(context, "InventoryApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTableString = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_PHONE_NUM + " INTEGER, " +
                COLUMN_USER_SMS_FLAG + " INTEGER)";
        String createInvTableString = "CREATE TABLE " + INVENTORY_TABLE + " (" + COLUMN_ITEM_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_COUNT + " INTEGER)";
        sqLiteDatabase.execSQL(createUserTableString);
        sqLiteDatabase.execSQL(createInvTableString);
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
        contentValues.put(COLUMN_USER_PHONE_NUM, newUser.getUserPhoneNum());
        contentValues.put(COLUMN_USER_SMS_FLAG, newUser.isSmsFlag());

        long insert = invAppDB.insert(USER_TABLE, null, contentValues);
        return insert != -1;  //true
    }

    //Add item to ITEM_TABLE
    public boolean addItem(inventoryItemModel newItem) {
        SQLiteDatabase invAppDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ITEM_NAME, newItem.getItemName());
        contentValues.put(COLUMN_ITEM_COUNT, newItem.getItemCount());

        long insert = invAppDB.insert(INVENTORY_TABLE, null, contentValues);
        return insert != -1;  //true
    }

    //search the db for username/password combo and return true if found
    public boolean checkDBforUser(InventoryUser newUser, boolean userANDpassword) {
        SQLiteDatabase invAppDB = this.getReadableDatabase();
        String DBquery = String.format("SELECT * FROM %s WHERE %s='%s' AND %s='%s'", USER_TABLE,
                COLUMN_USER_NAME, newUser.getInvUserName(), COLUMN_USER_PASSWORD, newUser.getInvUserPassword());
        if (!userANDpassword) {
            DBquery = String.format("SELECT * FROM %s WHERE %s='%s'", USER_TABLE,
                    COLUMN_USER_NAME, newUser.getInvUserName());
        }

        Cursor cursor = invAppDB.rawQuery(DBquery, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            invAppDB.close();
            return true;
        } else {
            cursor.close();
            invAppDB.close();
            return false;
        }
    }

    //search the INVENTORY_TABLE for an item (to avoid adding duplicates)
    public boolean checkDBforItem(inventoryItemModel newItem) {
        SQLiteDatabase invAppDB = this.getReadableDatabase();
        String DBquery = String.format("SELECT * FROM %s WHERE %s='%s'", INVENTORY_TABLE,
                COLUMN_ITEM_NAME, newItem.getItemName());

        Cursor cursor = invAppDB.rawQuery(DBquery, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            invAppDB.close();
            return true;
        } else {
            cursor.close();
            invAppDB.close();
            return false;
        }
    }

    public List<inventoryItemModel> getAllItemsInInventory() {
        String DBquery = "SELECT * FROM " + INVENTORY_TABLE;
        SQLiteDatabase invAppDB = this.getReadableDatabase();
        List<inventoryItemModel> inventoryList = new ArrayList<>();

        Cursor cursor = invAppDB.rawQuery(DBquery, null);

        if (cursor.moveToFirst()) {
            do {
                inventoryItemModel newItem = new inventoryItemModel(cursor.getString(1), cursor.getInt(2));
                inventoryList.add(newItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        invAppDB.close();
        return inventoryList;
    }

    public boolean deleteInvItem(String itemName) {
        SQLiteDatabase invAppDB = this.getWritableDatabase();
        int deleteSuccess = invAppDB.delete(INVENTORY_TABLE, COLUMN_ITEM_NAME + "=?", new String[] {itemName});
        invAppDB.close();
        if (deleteSuccess > 0) {
            return true;
        }
        return false;
    }

    public void updateItemCount(inventoryItemModel updateItem) {
        SQLiteDatabase invAppDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_COUNT, updateItem.getItemCount());
        invAppDB.update(INVENTORY_TABLE, contentValues, COLUMN_ITEM_NAME + "=?",
                new String[] {updateItem.getItemName()});
        invAppDB.close();
    }

    //update a users phone number in the database
    public void updateUserPhoneNum(String userName, Long userPhone) {
        SQLiteDatabase invAppDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("username: ", userName);
        Log.d("Phone num: ", String.valueOf(userPhone));
        contentValues.put(COLUMN_USER_PHONE_NUM, userPhone);
        invAppDB.update(USER_TABLE, contentValues, COLUMN_USER_NAME + "=?",
                new String[] {userName});
        invAppDB.close();
    }

    public Long getUserPhoneNum(String userName) {
        SQLiteDatabase invAppDB = this.getReadableDatabase();
        String DBquery = String.format("SELECT " + COLUMN_USER_PHONE_NUM + " FROM %s WHERE %s='%s'", USER_TABLE,
                COLUMN_USER_NAME, userName);

        Cursor cursor = invAppDB.rawQuery(DBquery, null);
        cursor.moveToFirst();
        Long phoneNum = cursor.getLong(0);
        cursor.close();
        invAppDB.close();
        return phoneNum;
    }

    public void updateUserSMSflag(String userName, int newFlag) {
        SQLiteDatabase invAppDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_SMS_FLAG, newFlag);
        invAppDB.update(USER_TABLE, contentValues, COLUMN_USER_NAME + "=?",
                new String[] {userName});
        invAppDB.close();
    }

    public boolean getUserSMSflag(String userName) {
        SQLiteDatabase invAppDB = this.getReadableDatabase();
        String DBquery = String.format("SELECT " + COLUMN_USER_SMS_FLAG + " FROM %s WHERE %s='%s'", USER_TABLE,
                COLUMN_USER_NAME, userName);

        Cursor cursor = invAppDB.rawQuery(DBquery, null);
        cursor.moveToFirst();
        int data = cursor.getInt(0);
        cursor.close();
        invAppDB.close();
        return data == 1;
    }
}
