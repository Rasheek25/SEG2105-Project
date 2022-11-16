package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructorFunction extends AppCompatActivity {

    Button logoutBtn;
    Button searchCourseBtn;
    TextView welcomeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_function);

        logoutBtn = findViewById(R.id.logOut);
        searchCourseBtn = findViewById(R.id.SearchCourse);
        welcomeTxt = findViewById(R.id.welcomeUser);
        welcomeTxt.setText("Welcome " + MainActivity.currentUser.getUsername() + " !");


        // button listeners
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InstructorFunction.this, MainActivity.class));
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InstructorFunction.this, SearchCourse.class));
            }
        });
    }
}