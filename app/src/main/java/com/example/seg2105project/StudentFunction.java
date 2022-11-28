package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;
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

import android.os.Bundle;

public class StudentFunction extends AppCompatActivity {

    Button logoutBtn, searchCourseBtn, myCoursesBtn;
    TextView welcomeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_function);
        getSupportActionBar().hide();

        logoutBtn = findViewById(R.id.logOut);
        welcomeTxt = findViewById(R.id.welcomeUser);
        searchCourseBtn = findViewById(R.id.SearchCourse);
        myCoursesBtn = findViewById(R.id.myCourses);
        welcomeTxt.setText("Welcome " + MainActivity.currentUser.getUsername() + " !");

        // button listeners
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentFunction.this, MainActivity.class));
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentFunction.this, StudentSearchCourse.class));
            }
        });

        myCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentFunction.this, StudentMyCourses.class));
            }
        });


    }
}

