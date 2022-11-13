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

public class EditCourse2 extends AppCompatActivity {

    Button editCourseBtn, homeBtn;
    EditText newCourseCodeET, newCourseNameET;
    TextView oldCourseCode, oldCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course2);

        homeBtn = findViewById(R.id.home);
        editCourseBtn = findViewById(R.id.edit);
        newCourseCodeET = findViewById(R.id.courseCode);
        newCourseNameET = findViewById(R.id.courseName);
        oldCourseCode = findViewById(R.id.searchCode);
        oldCourseName = findViewById(R.id.searchName);
        Admin admin = new Admin();
        DBHandler myDBHandler = new DBHandler(this);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditCourse2.this, AdminFunction.class));
            }
        });

        oldCourseCode.setText(EditCourse.courseToBoDeleted.getCourseCode());
        oldCourseName.setText(EditCourse.courseToBoDeleted.getCourseName());

        editCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newCourseCodeET.getText().equals("") || newCourseNameET.getText().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter course information",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    admin.editCourse(new Course(EditCourse.courseToBoDeleted.getCourseCode(), EditCourse.courseToBoDeleted.getCourseName() ),
                            new Course(newCourseCodeET.getText().toString(), newCourseNameET.getText().toString()));
                    Toast.makeText(getApplicationContext(),
                            "Course edited successfully",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}