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

public class SearchCourse extends AppCompatActivity {

    EditText courseCodeET, courseNameET;
    Button searchCourseBtn, menuBtn;
    ListView courseListView;
    static Course selectedCourse;

    ArrayList<String> courseList;
    ArrayAdapter adapter;
    DBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);
        getSupportActionBar().hide();

        Admin admin = new Admin();
        myDBHandler = new DBHandler(this);
        courseCodeET = findViewById(R.id.courseCode);
        courseNameET = findViewById(R.id.courseName);
        searchCourseBtn = findViewById(R.id.searchCourse);
        menuBtn = findViewById(R.id.menu);
        courseListView = findViewById(R.id.courseListView);
        courseList = new ArrayList<>();


        viewCourses();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchCourse.this, InstructorFunction.class));
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = null;
                if (courseCodeET.getText().toString().equals("") && courseNameET.getText().toString().equals("")) {
                    viewCourses();
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
                    Toast.makeText(SearchCourse.this, "No match found", Toast.LENGTH_SHORT).show();

                }


            }

        });

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String selectedCourseStr = adapter.getItem(i).toString();
                String[] arr = selectedCourseStr.split(" : ");
                selectedCourse = new Course(arr[0], arr[1]);
                startActivity(new Intent(SearchCourse.this, CourseMenu.class));

            }
        });





    }

    private void viewCourses() {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(SearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(SearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(SearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(SearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
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



}
