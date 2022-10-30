package com.example.seg2105project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String DATABASE_NAME = "studentAppDB";
    private static final int DATABASE_VERSION = 2;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT " + ")";

        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public boolean UserFound(User user){
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + user.getUsername() + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + user.getPassword();
        Cursor cursor = db.rawQuery(query, null );

        if(cursor.moveToFirst()){
            found = true;
        }
        db.close();
        return found;
    }

    public boolean deleteUser(User user){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + user.getUsername() + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + user.getPassword();
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_NAME, COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }
}

