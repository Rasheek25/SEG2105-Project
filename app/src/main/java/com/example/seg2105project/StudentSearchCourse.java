package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

import java.util.ArrayList;

public class StudentSearchCourse extends AppCompatActivity {

    EditText courseCodeET, courseNameET, courseDayET;
    Button searchCourseBtn, menuBtn;
    ListView courseListView;
    static Course selectedCourse;

    ArrayList<String> courseList;
    ArrayAdapter adapter;
    DBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_course);
        getSupportActionBar().hide();

        Admin admin = new Admin();
        myDBHandler = new DBHandler(this);
        courseCodeET = findViewById(R.id.courseCode);
        courseNameET = findViewById(R.id.courseName);
        courseDayET = findViewById(R.id.courseDay);
        searchCourseBtn = findViewById(R.id.searchCourse);
        menuBtn = findViewById(R.id.menu);
        courseListView = findViewById(R.id.courseListView);
        courseList = new ArrayList<>();


        viewCourses();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentSearchCourse.this, StudentFunction.class));
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = null;
                if (courseCodeET.getText().toString().equals("") && courseNameET.getText().toString().equals("") && courseDayET.getText().toString().equals("") ) {
                    viewCourses();
                }
                else if (courseCodeET.getText().toString().equals("") && courseNameET.getText().toString().equals("")) {
                    FindViewByCourseDay(courseDayET.getText().toString());
                } else if (courseNameET.getText().toString().equals("")) {
                    course = myDBHandler.findByCourseCode(courseCodeET.getText().toString());
                    FindViewByCourseCode(course);
                } else if (courseCodeET.getText().toString().equals("")) {
                    course = myDBHandler.findByCourseName(courseNameET.getText().toString());
                    FindViewByCourseName(course);
                }


                else {
                    course = myDBHandler.findByCourseCode(courseCodeET.getText().toString());
                    FindView(course);
                }
                if (course == null) {
                    Toast.makeText(StudentSearchCourse.this, "No match found", Toast.LENGTH_SHORT).show();

                }


            }

        });

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String selectedCourseStr = adapter.getItem(i).toString();
                String[] arr = selectedCourseStr.split(" : ");
                selectedCourse = new Course(arr[0], arr[1]);
                startActivity(new Intent(StudentSearchCourse.this, CourseMenu.class));

            }
        });





    }

    private void viewCourses() {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentSearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                courseList.add(cursor.getString(1) + ' ' + ":" + ' ' + cursor.getString(2));

            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);
    }

    private void FindView(Course course) {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentSearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                if (course.getCourseCode().equals(cursor.getString(1)) && course.getCourseName().equals(cursor.getString(2))) {
                    courseList.add(cursor.getString(1)+ ' ' + ":" + ' ' + cursor.getString(2));
                }
            }
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);

    }

    private void FindViewByCourseName(Course course) {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentSearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                if (course.getCourseName().equals(cursor.getString(2))) {
                    courseList.add(cursor.getString(1) + ' ' + ":" + ' ' + cursor.getString(2));
                }
            }
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);

    }

    private void FindViewByCourseCode(Course course) {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentSearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                if (course.getCourseCode().equals(cursor.getString(1))) {
                    courseList.add(cursor.getString(1) + ' ' + ":" + ' ' + cursor.getString(2));
                }
            }
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);


    }

    private void FindViewByCourseDay(String day) {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentSearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                ArrayList<String> courseDays = new ArrayList<>();

                //filtering the course days from the schedukle
                String[] days = cursor.getString(6).split(" | ");
                for (String d : days ){
                    String[] temp = d.split(" : ");
                    courseDays.add(temp[0]);
                }

                for(String g : courseDays){
                    if(day.equals(g)){
                        courseList.add(cursor.getString(1) + ' ' + ":" + ' ' + cursor.getString(2));
                    }
                }
            }
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);


    }



}