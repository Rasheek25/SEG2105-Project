package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandlerAccounts extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";
    private static final String DATABASE_NAME = "courses.db";
    private static final int DATABASE_VERSION = 5;

    public MyDBHandlerAccounts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_ROLE + " TEXT " + ")";

        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COLUMN_USERNAME, account.getAccountName());
        values.put(COLUMN_PASSWORD, account.getAccountPassword());
        values.put(COLUMN_ROLE, account.getAccountRole());




        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Account findAccount(String accountName) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + accountName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();
        if (cursor.moveToFirst()) {
            //product.setId(Integer.parseInt(cursor.getString(0)));
            account.setAccountName((cursor.getString(1)));
            account.setAccountPassword((cursor.getString(2)));
            account.setAccountRole((cursor.getString(3)));
            cursor.close();
        }

        else {
            account = null;
        }

        db.close();
        return account;
    }

    public boolean deleteAccount(String userName) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + userName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_NAME, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }
}