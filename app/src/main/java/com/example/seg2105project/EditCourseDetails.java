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

public class EditCourseDetails extends AppCompatActivity {

    Button backBtn, addDayBtn, resetScheduleBtn, editDescriptionBtn, editCapacityBtn;
    EditText dayET, startTimeET, endTimeET, descriptionET, capacityET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_details);

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





    }
}