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
import java.lang.StringBuffer;


import android.os.Bundle;

public class CourseMenu extends AppCompatActivity {

    Button backBtn, editCourseBtn, assignBtn;
    TextView courseCodeText, courseNameText, courseInstructorText, courseScheduleText, courseCapacityText, courseDescriptionText;
    Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);
        getSupportActionBar().hide();

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
                //User is Instructor
                if(selectedCourse.getInstructor() == null) {
                    selectedCourse.assign((Instructor) MainActivity.currentUser);
                    Toast.makeText(getApplicationContext(),
                            "Assigned to course successfully",
                            Toast.LENGTH_LONG).show();
                    update();
                    UserRightsCheck();
                }
                //user is student
                else if (MainActivity.currentUser instanceof Instructor){
                    Student currentStudent = (Student)MainActivity.currentUser;
                    //student is enrolled
                    if( currentStudent.isEnrolled(selectedCourse)) {
                        currentStudent.drop(selectedCourse);
                        Toast.makeText(getApplicationContext(),
                                "Droped course successfully",
                                Toast.LENGTH_LONG).show();
                        update();
                        UserRightsCheck();
                    }

                    //student is enrolled
                    else if ( !currentStudent.isEnrolled(selectedCourse)) {
                        currentStudent.enroll(selectedCourse);
                        Toast.makeText(getApplicationContext(),
                                "Enrolled to course successfully",
                                Toast.LENGTH_LONG).show();
                        update();
                        UserRightsCheck();
                    }


                }
                else if (selectedCourse.getInstructorName().equals(MainActivity.currentUser.getUsername())){
                    selectedCourse.unassign();
                    Toast.makeText(getApplicationContext(),
                            "Un-assigned to course successfully",
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
        if(selectedCourse.getInstructorName().equals("N/A")){
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

        //user is student
        else if (MainActivity.currentUser instanceof Instructor){
            Student currentStudent = (Student)MainActivity.currentUser;
            //student is enrolled
            if( currentStudent.isEnrolled(selectedCourse)) {
                editCourseBtn.setVisibility(View.GONE);
                assignBtn.setText("enroll");
            }

            //student is enrolled
            else if ( !currentStudent.isEnrolled(selectedCourse)) {
                editCourseBtn.setVisibility(View.GONE);
                assignBtn.setText("Drop Course");
            }

        }

        //course has other instructor
        else if (!selectedCourse.getInstructorName().equals(MainActivity.currentUser.getUsername())){
            editCourseBtn.setVisibility(View.GONE);
            assignBtn.setVisibility(View.GONE);

        }




    }

    private void update(){
       /* if(selectedCourse.getCourseDescription() == null) {
            courseDescriptionText.setText("N/A");
            selectedCourse.setCourseDescription("N/A");
        }
        else{*/
            courseDescriptionText.setText(selectedCourse.getCourseDescription());
        //}
        courseCodeText.setText(selectedCourse.getCourseCode());
        courseNameText.setText(selectedCourse.getCourseName());
        courseInstructorText.setText(selectedCourse.getInstructorName());
        courseScheduleText.setText(toDisplayableString(selectedCourse.getCourseSchedule()));

        if(selectedCourse.getStudentCapacity() == 0){
            courseCapacityText.setText("N/A");
        }
        else{
            courseCapacityText.setText(selectedCourse.getStudentCapacity().toString());
        }
    }

    private String toDisplayableString(String str){
        /*if(str == null){
            return "N/A";
        }*/
        if(str.equals("N/A")){
            return "N/A";
        }
        else {
            StringBuffer buffer = new StringBuffer(str);
            //removing last ";"
            buffer.deleteCharAt(buffer.length() - 2);
            return buffer.toString();
        }

    }


}