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


import android.os.Bundle;

public class CourseMenu extends AppCompatActivity {

    Button backBtn, editCourseBtn, assignBtn;
    TextView courseCodeText, courseNameText, courseInstructorText, courseScheduleText, courseCapacityText, courseDescriptionText;
    Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);

        selectedCourse = SearchCourse.selectedCourse;
        backBtn = (Button) findViewById(R.id.back);
        assignBtn = (Button) findViewById(R.id.assign);
        editCourseBtn = (Button) findViewById(R.id.edit);
        courseCodeText = findViewById(R.id.CourseCode);
        courseNameText = findViewById(R.id.CourseName);
        courseInstructorText =  findViewById(R.id.instructor);
        courseScheduleText =  findViewById(R.id.day);
        courseCapacityText =  findViewById(R.id.capacity);
        courseDescriptionText =  findViewById(R.id.description);

        update();
        UserRightsCheck();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseMenu.this, SearchCourse.class));
            }
        });

        assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //assign case
                if(selectedCourse.getInstructor() == null) {
                    selectedCourse.assign((Instructor) MainActivity.currentUser);
                    Toast.makeText(getApplicationContext(),
                            "Assigned to course Successfully",
                            Toast.LENGTH_LONG).show();
                    update();
                    UserRightsCheck();
                }
                else if (selectedCourse.getInstructorName().equals(MainActivity.currentUser.getUsername())){
                    selectedCourse.unassign();
                    Toast.makeText(getApplicationContext(),
                            "Un-Assigned to course Successfully",
                            Toast.LENGTH_LONG).show();
                    update();
                    UserRightsCheck();

                }
            }
        });

        editCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseMenu.this, EditCourseDetails.class));
            }
        });


    }

    private void UserRightsCheck(){

        //course has no instructor
        if(selectedCourse.getInstructor() == null){
            assignBtn.setText("assign to Course");
            assignBtn.setVisibility(View.VISIBLE);
            editCourseBtn.setVisibility(View.GONE);
        }

        //current instructor already assigned
        else if(selectedCourse.getInstructorName().equals(MainActivity.currentUser.getUsername())){
            assignBtn.setText("un-assign to course");
            editCourseBtn.setVisibility(View.VISIBLE);
            assignBtn.setVisibility(View.VISIBLE);

        }

        //course has other instructor
        else{
            editCourseBtn.setVisibility(View.GONE);
            assignBtn.setVisibility(View.GONE);

        }
    }

    private void update(){
        courseCodeText.setText(selectedCourse.getCourseCode());
        courseNameText.setText(selectedCourse.getCourseName());
        courseInstructorText.setText(selectedCourse.getInstructorName());
        courseScheduleText.setText(selectedCourse.getCourseSchedule());
        courseDescriptionText.setText(selectedCourse.getCourseDescription());
        if(selectedCourse.getStudentCapacity() == 0){
            courseCapacityText.setText("N/A");
        }
        else{
            courseCapacityText.setText(selectedCourse.getStudentCapacity().toString());
        }
    }


}