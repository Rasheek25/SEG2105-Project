package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCourse extends AppCompatActivity {

    Button homeBtn, addCourseBtn;
    EditText courseCodeET, courseNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        DBHandler myDBHandler = new DBHandler(this);
        homeBtn = findViewById(R.id.home);
        addCourseBtn = findViewById(R.id.addCourse);
        courseCodeET = findViewById(R.id.courseCode);
        courseNameET = findViewById(R.id.courseName);
        Admin admin = new Admin();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateCourse.this, AdminFunction.class));
            }
        });

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (courseCodeET.getText().equals("") || courseNameET.getText().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter course information.",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    admin.createCourse(new Course(courseCodeET.getText().toString(), courseNameET.getText().toString()));
                    //myDBHandler.addCourse(new Course(courseCodeET.getText().toString(), courseNameET.getText().toString()));
                    Toast.makeText(getApplicationContext(),
                            "Course created successfully",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}