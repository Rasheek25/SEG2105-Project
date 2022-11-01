package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "courses";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COURSE_NAME = "name";
    private static final String COLUMN_COURSE_CODE = "code";
    private static final String DATABASE_NAME = "courses.db";
    private static final int DATABASE_VERSION = 5;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_COURSE_NAME + " TEXT, " +
                COLUMN_COURSE_CODE + " TEXT " + ")";

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

    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COLUMN_COURSE_NAME, course.getCourseName());
        values.put(COLUMN_COURSE_CODE, course.getCourseCode());




        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Course findCourse(String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_COURSE_NAME + "=\"" + courseName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Course course = new Course();
        if (cursor.moveToFirst()) {
            //product.setId(Integer.parseInt(cursor.getString(0)));
            course.setCourseName(cursor.getString(1));
            course.setCourseCode(cursor.getString(2));
            cursor.close();
        }

        else {
            course = null;
        }

        db.close();
        return course;
    }

    public boolean deleteCourse(String courseName) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_COURSE_NAME + "=\"" + courseName + "\"";
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