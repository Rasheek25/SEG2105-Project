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

public class EditCourseDetails extends AppCompatActivity {

    Button backBtn, addDayBtn, resetScheduleBtn, editDescriptionBtn, editCapacityBtn;
    EditText dayET, startTimeET, endTimeET, descriptionET, capacityET;
    Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_details);
        selectedCourse = SearchCourse.selectedCourse;
        getSupportActionBar().hide();

        //buttons
        backBtn = (Button) findViewById(R.id.back);
        addDayBtn = (Button) findViewById(R.id.addDay);
        resetScheduleBtn = (Button) findViewById(R.id.resetDay);
        editDescriptionBtn = (Button) findViewById(R.id.addDescription);
        editCapacityBtn = (Button) findViewById(R.id.addCapacity);

        //TextEdits
        dayET = findViewById(R.id.Day);
        startTimeET = findViewById(R.id.startTime);
        endTimeET = findViewById(R.id.endTime);
        descriptionET = findViewById(R.id.description);
        capacityET = findViewById(R.id.capacity);


        //onClick

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditCourseDetails.this, CourseMenu.class));
            }
        });

        addDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dayET.getText().toString().equals("") &&
                        !startTimeET.getText().toString().equals("") &&
                        !endTimeET.getText().toString().equals("")) {

                    selectedCourse.setCourseSchedule(dateToString(dayET, startTimeET, endTimeET));
                    dayET.setText("");
                    startTimeET.setText("");
                    endTimeET.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Invalid input",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        resetScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCourse.setCourseSchedule("N/A");
                Toast.makeText(getApplicationContext(),
                        "Schedule reset",
                        Toast.LENGTH_LONG).show();
            }
        });

        editDescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCourse.setCourseDescription(descriptionET.getText().toString());
                Toast.makeText(getApplicationContext(),
                        "Description edited successfully",
                        Toast.LENGTH_LONG).show();
            }
        });

        editCapacityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(Integer.parseInt(capacityET.getText().toString()) <= 0){
                        Toast.makeText(getApplicationContext(),
                                "Invalid input",
                                Toast.LENGTH_LONG).show();

                    }
                    else{
                    selectedCourse.setStudentCapacity(Integer.parseInt(capacityET.getText().toString()));
                    Toast.makeText(getApplicationContext(),
                            "Capacity edited successfully",
                            Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            "Invalid input",
                            Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    //date and hours --> "day{startTime - endTime};" format
    /*private String dateToString(EditText day, EditText startTime, EditText endTime){
        String previous = selectedCourse.getCourseSchedule();
        if (previous.equals("N/A")){
            previous = "";
        }
        StringBuffer buffer = new StringBuffer(previous);
        buffer.append(day.getText().toString());
        buffer.append("{");
        buffer.append(startTime.getText().toString());
        buffer.append("-");
        buffer.append(endTime.getText().toString());
        buffer.append("}");
        buffer.append(";");
        return buffer.toString();
    }*/

    //date and hours --> "day : startTime - endTime | " format
    private String dateToString(EditText day, EditText startTime, EditText endTime){
        String previous = selectedCourse.getCourseSchedule();
        if (previous.equals("N/A")){
            previous = "";
        }
        StringBuffer buffer = new StringBuffer(previous);
        buffer.append(day.getText().toString());
        buffer.append(" : ");
        buffer.append(startTime.getText().toString());
        buffer.append(" - ");
        buffer.append(endTime.getText().toString());
        buffer.append(" | ");
        return buffer.toString();
    }

}