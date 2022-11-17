package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminFunction extends AppCompatActivity {

    Button logoutBtn, createCourseBtn, editCourseBtn, deleteCourseBtn, deleteStudentAccountBtn, deleteInstructorAccountBtn;

    TextView welcomeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_function);
        getSupportActionBar().hide();



        //buttons
        logoutBtn = findViewById(R.id.logOut);
        welcomeTxt = findViewById(R.id.welcomeUser);
        createCourseBtn = findViewById(R.id.createCourse);
        editCourseBtn = findViewById(R.id.editCourse);
        deleteCourseBtn = findViewById(R.id.deleteCourse);
        deleteStudentAccountBtn= findViewById(R.id.deleteStudentAccount);
        deleteInstructorAccountBtn= findViewById(R.id.deleteInstructorAccount);

        welcomeTxt.setText("Welcome " + MainActivity.currentUser.getUsername() + " !");

        // button listeners
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminFunction.this, MainActivity.class));
            }
        });

        createCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunction.this, CreateCourse.class));
            }
        });

        editCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunction.this, EditCourse.class));
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunction.this, DeleteCourse.class));
            }
        });

        deleteStudentAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunction.this, DeleteStudentAccount.class));
            }
        });

        deleteInstructorAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunction.this, DeleteInstructorAccount.class));
            }
        });
    }
}