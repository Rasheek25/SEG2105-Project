package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class CreateCourse extends AppCompatActivity {

    EditText courseName, courseCode;
    MyDBHandler dbHandlerCourse;
    Button createCourse, home;
   /* ListView productListView;

    ArrayList<String> productList;
    ArrayAdapter adapter;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);


        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);
        createCourse = findViewById(R.id.addCourse);
        home = findViewById(R.id.home);

        dbHandlerCourse = new MyDBHandler(this);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateCourse.this, AdminFunction.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CourseName = courseName.getText().toString();
                String CourseCode = courseCode.getText().toString();

                Course course = new Course(CourseName, CourseCode);
                dbHandlerCourse.addCourse(course);

                courseName.setText("");
                courseCode.setText("");
                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();

            }



        });


    }


}