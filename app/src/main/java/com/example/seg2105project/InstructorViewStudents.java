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

public class InstructorViewStudents extends AppCompatActivity {


    Button  backBtn;
    TextView courseCodeTV, courseNameTV;
    ListView studentListView;
    static Course selectedCourse;

    ArrayList<String> studentList;
    ArrayAdapter adapter;
    DBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_view_students);
        getSupportActionBar().hide();

        Admin admin = new Admin();
        myDBHandler = new DBHandler(this);
        backBtn = findViewById(R.id.back);
        studentListView = findViewById(R.id.studentListView);
        courseCodeTV = findViewById(R.id.CourseCode);
        courseNameTV = findViewById(R.id.CourseName);
        studentList = new ArrayList<>();
        selectedCourse = SearchCourse.selectedCourse;


        courseCodeTV.setText(selectedCourse.getCourseCode());
        courseNameTV.setText(selectedCourse.getCourseName());

        viewStudents();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InstructorViewStudents.this, CourseMenu.class));
            }
        });


    }

    private void viewStudents() {
        studentList.clear();
        String courses = myDBHandler.getEnrolledStudents(selectedCourse);
        if (courses.equals("N/A") || courses.equals("")) {
            Toast.makeText(InstructorViewStudents.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            String[] coursesArr = courses.split(";");
            List<String> coursesList = Arrays.asList(coursesArr);
            coursesList.remove("");
            for(String c: coursesList){
                studentList.add(c);

            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        studentListView.setAdapter(adapter);
    }





}