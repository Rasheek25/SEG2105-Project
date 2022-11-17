package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class DeleteCourse extends AppCompatActivity {

    Button homeBtn, deleteCourseBtn, searchCourseBtn;
    TextView courseCodeText, courseNameText  ;
    EditText courseCodeET, courseNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        getSupportActionBar().hide();

        homeBtn = findViewById(R.id.home);
        searchCourseBtn = findViewById(R.id.searchCourse);
        deleteCourseBtn = findViewById(R.id.deleteCourse);
        courseCodeText = findViewById(R.id.searchCode);
        courseNameText = findViewById(R.id.searchName);
        courseCodeET = findViewById(R.id.courseCode);
        courseNameET = findViewById(R.id.courseName);
        Admin admin = new Admin();
        DBHandler myDBHandler = new DBHandler(this);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteCourse.this, AdminFunction.class));
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //delete button clicked without entering username
                deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),
                                "Please enter username.",
                                Toast.LENGTH_LONG).show();
                    }
                });

                if(myDBHandler.courseFound(courseCodeET.getText().toString(), courseNameET.getText().toString())){
                    courseCodeText.setText(courseCodeET.getText());
                    courseNameText.setText(courseNameET.getText());

                    deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(admin.deleteCourse(new Course(courseCodeET.getText().toString(), courseNameET.getText().toString()))){
                                Toast.makeText(getApplicationContext(),
                                        "Course deleted successfully",
                                        Toast.LENGTH_LONG).show();
                            }
                            else if(courseCodeET.getText().equals("") || courseNameET.getText().equals("") ){
                                Toast.makeText(getApplicationContext(),
                                        "Please enter course information",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Course not found. Please try again.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}