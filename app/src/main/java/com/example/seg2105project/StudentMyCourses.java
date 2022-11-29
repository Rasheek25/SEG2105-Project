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
import java.util.Arrays;
import java.util.List;

public class StudentMyCourses extends AppCompatActivity {


    Button  menuBtn;
    ListView courseListView;
    static Course selectedCourse;

    ArrayList<String> courseList;
    ArrayAdapter adapter;
    DBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_my_courses);
        getSupportActionBar().hide();

        Admin admin = new Admin();
        myDBHandler = new DBHandler(this);
        menuBtn = findViewById(R.id.menu);
        courseListView = findViewById(R.id.courseListView);
        courseList = new ArrayList<>();


        viewCourses();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMyCourses.this, StudentFunction.class));
            }
        });


        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String selectedCourseStr = adapter.getItem(i).toString();
                String[] arr = selectedCourseStr.split(" : ");
                selectedCourse = new Course(arr[0], arr[1]);
                startActivity(new Intent(StudentMyCourses.this, CourseMenu.class));

            }
        });





    }

    private void viewCourses() {
        courseList.clear();
        String courses = myDBHandler.getStudentCourses(MainActivity.currentUser);
        if (courses.equals("N/A") || courses.equals("")) {
            Toast.makeText(StudentMyCourses.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            String[] coursesArr = courses.split(";");
            List<String> coursesList = Arrays.asList(coursesArr);
            coursesList.remove("");
            for(String c: coursesList){
                courseList.add(c);

            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);
    }

    private void FindView(Course course) {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentMyCourses.this, "Nothing to show", Toast.LENGTH_SHORT).show();
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




}