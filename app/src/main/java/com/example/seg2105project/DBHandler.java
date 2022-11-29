package com.example.seg2105project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    //users table
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "userId";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_COURSES = "Courses";

    //courses table
    private static final String COURSES_TABLE_NAME = "courses";
    private static final String COURSE_COLUMN_ID = "courseId";
    private static final String COLUMN_COURSE_CODE = "code";
    private static final String COLUMN_COURSE_NAME = "name";
    private static final String COLUMN_COURSE_INSTRUCTOR = "instructor";
    private static final String COLUMN_COURSE_CAPACITY = "capacity";
    private static final String COLUMN_COURSE_DESCRIPTION = "description";
    private static final String COLUMN_COURSE_SCHEDULE= "schedule";
    private static final String COLUMN_ENROLLED_STUDENTS= "students";
    private static final String COLUMN_STUDENT_COUNT= "student_count";


    private static final String DATABASE_NAME = "studentAppDB.sqlite";
    private static final int DATABASE_VERSION = 15;



    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_COURSES + " TEXT " + ")";

        String create_courses_table_cmd = "CREATE TABLE " + COURSES_TABLE_NAME +
                "(" + COURSE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_COURSE_CODE + " TEXT, " +
                COLUMN_COURSE_NAME + " TEXT, " +
                COLUMN_COURSE_INSTRUCTOR + " TEXT, " +
                COLUMN_COURSE_CAPACITY + " INTEGER, " +
                COLUMN_COURSE_DESCRIPTION + " TEXT, " +
                COLUMN_COURSE_SCHEDULE + " TEXT, " +
                COLUMN_ENROLLED_STUDENTS + " TEXT, " +
                COLUMN_STUDENT_COUNT + " INTEGER " + ")";

        db.execSQL(create_users_table_cmd);
        db.execSQL(create_courses_table_cmd);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE_NAME);
        onCreate(db);
    }


    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public Cursor getCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ROLE, user.getRole());
        values.put(COLUMN_COURSES, "N/A");

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_COURSE_CODE, course.getCourseCode());
        values.put(COLUMN_COURSE_NAME, course.getCourseName());
        values.put(COLUMN_COURSE_INSTRUCTOR, "N/A");
        values.put(COLUMN_COURSE_CAPACITY, 0);
        values.put(COLUMN_COURSE_DESCRIPTION, "N/A");
        values.put(COLUMN_COURSE_SCHEDULE, "N/A");
        values.put(COLUMN_ENROLLED_STUDENTS, "N/A");
        values.put(COLUMN_STUDENT_COUNT, 0);

        db.insert(COURSES_TABLE_NAME, null, values);
        db.close();
    }

    public boolean UserFound(User user){
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + user.getUsername() + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + user.getPassword()+"\"";
        Cursor cursor = db.rawQuery(query, null );

        if(cursor.moveToFirst()){
            found = true;
        }
        db.close();
        return found;
    }

    public boolean courseFound(Course course){
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );

        if(cursor.moveToFirst()){
            found = true;
        }
        db.close();
        return found;
    }

    public String getUserRole(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +  " WHERE " + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String r = null;

        if (cursor.moveToFirst()) {
            r = cursor.getString(3);
            cursor.close();
        }
        db.close();
        return r;
    }


    public boolean UserFound(String username, String password){
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + password+"\"";
        Cursor cursor = db.rawQuery(query, null );

        if(cursor.moveToFirst()){
            found = true;
            cursor.close();
        }
        db.close();
        return found;
    }

    public boolean courseFound(String courseCode, String courseName){
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + courseCode + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + courseName+"\"";
        Cursor cursor = db.rawQuery(query, null );

        if(cursor.moveToFirst()){
            found = true;
            cursor.close();
        }
        db.close();
        return found;
    }

    public boolean UserFound(String username){
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null );

        if(cursor.moveToFirst()){
            found = true;
            cursor.close();
        }
        db.close();
        return found;
    }



    public boolean deleteUser(String username){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + username+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_NAME, COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean deleteUser(User user){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + user.getUsername()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_NAME, COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean deleteCourse(Course course){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(COURSES_TABLE_NAME, COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }


    //course editing methods
    public boolean editCourse(Course oldCourse, Course newCourse){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + oldCourse.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + oldCourse.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSE_CODE, newCourse.getCourseCode());
            contentValues.put(COLUMN_COURSE_NAME, newCourse.getCourseName());
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editCourseInstructor(Course course, String newInstructor){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSE_INSTRUCTOR, newInstructor);
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editCourseCapacity(Course course, Integer capacity){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSE_CAPACITY, capacity);
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editCourseDescription(Course course, String description){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSE_DESCRIPTION, description);
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editEnrolledStudents(Course course, String students){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ENROLLED_STUDENTS, students);
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editCourseSchedule(Course course, String schedule){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSE_SCHEDULE, schedule);
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editStudentCourses(User user, String courses){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + user.getUsername() + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + user.getPassword()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSES, courses);
            db.update(TABLE_NAME, contentValues,COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editCourseCount(Course course, Integer newCount){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" +" AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_STUDENT_COUNT, newCount);
            db.update(COURSES_TABLE_NAME, contentValues,COURSE_COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean editUserCourses(User user, String courses){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + user.getUsername() + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + user.getPassword()+"\"";
        Cursor cursor = db.rawQuery(query, null );
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_COURSES, courses);
            db.update(TABLE_NAME, contentValues,COLUMN_ID + "=" + idStr, null);
            cursor.close();
            result = true;
        }
        return result;
    }

    public String getCourseInstructor(Course course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME +  " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" + " AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String r = null;

        if (cursor.moveToFirst()) {
            r = cursor.getString(3);
            cursor.close();
        }
        db.close();
        return r;
    }

    public String getCourseDescription(Course course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME +  " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" + " AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String r = null;

        if (cursor.moveToFirst()) {
            r = cursor.getString(5);
            cursor.close();
        }
        db.close();
        return r;
    }

    public String getCourseSchedule(Course course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME +  " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" + " AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String r = null;

        if (cursor.moveToFirst()) {
            r = cursor.getString(6);
            cursor.close();
        }
        db.close();
        return r;
    }

    public Integer getCourseCapacity(Course course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME +  " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" + " AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Integer r = null;

        if (cursor.moveToFirst()) {
            r = Integer.parseInt(cursor.getString(4));
            cursor.close();
        }
        db.close();
        return r;
    }

    public String getStudentCourses(User student) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + student.getUsername() + "\"" +" AND " + COLUMN_PASSWORD + "=\"" + student.getPassword()+"\"";
        Cursor cursor = db.rawQuery(query, null);
        String r = null;

        if (cursor.moveToFirst()) {
            r = cursor.getString(4);
            cursor.close();
        }
        db.close();
        return r;
    }

    public Course findByCourseCode(String CourseCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_CODE + "=\"" + CourseCode + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Course course = new Course();
        if (cursor.moveToFirst()) {
            //product.setId(Integer.parseInt(cursor.getString(0)));
            course.setCourseCode(cursor.getString(1));
            course.setCourseName(cursor.getString(2));
            cursor.close();
        }

        else {
            course = null;
        }

        db.close();
        return course;
    }

    public Course findByCourseName(String CourseName) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COLUMN_COURSE_NAME + "=\"" + CourseName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Course course = new Course();
        if (cursor.moveToFirst()) {
            //product.setId(Integer.parseInt(cursor.getString(0)));
            course.setCourseCode(cursor.getString(1));
            course.setCourseName(cursor.getString(2));
            cursor.close();
        }

        else {
            course = null;
        }

        db.close();
        return course;
    }


    public String getEnrolledStudents(Course course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME +  " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" + " AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String r = null;

        if (cursor.moveToFirst()) {
            r = cursor.getString(7);
            cursor.close();
        }
        db.close();
        return r;
    }

    public Integer getStudentCount(Course course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME +  " WHERE " + COLUMN_COURSE_CODE + "=\"" + course.getCourseCode() + "\"" + " AND " + COLUMN_COURSE_NAME + "=\"" + course.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Integer r = null;

        if (cursor.moveToFirst()) {
            r = Integer.parseInt(cursor.getString(8));
            cursor.close();
        }
        db.close();
        return r;
    }
}





