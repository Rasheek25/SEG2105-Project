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

public class EditCourse extends AppCompatActivity {

    public static Course courseToBoDeleted;
    Button searchCourseBtn, homeBtn;
    EditText courseCodeET, courseNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        homeBtn = findViewById(R.id.home);
        searchCourseBtn = findViewById(R.id.searchCourse);
        courseCodeET = findViewById(R.id.courseCode);
        courseNameET = findViewById(R.id.courseName);
        DBHandler myDBHandler = new DBHandler(this);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditCourse.this, AdminFunction.class));
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(courseCodeET.getText().equals("") || courseNameET.getText().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter course information",
                            Toast.LENGTH_LONG).show();
                }
                if(myDBHandler.courseFound(courseCodeET.getText().toString(), courseNameET.getText().toString())){
                    courseToBoDeleted = new Course(courseCodeET.getText().toString(), courseNameET.getText().toString());
                    startActivity(new Intent(EditCourse.this, EditCourse2.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Course not found. Please try again",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}