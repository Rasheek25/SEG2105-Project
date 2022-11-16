package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AssignDetails extends AppCompatActivity {

    Button addDay, addDescription, addCapacity, complete, homeBtn;
    EditText day, startTime, endTime, courseDescription, courseCapacity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_details);

        //Buttons
        homeBtn = findViewById(R.id.home);
        addDay = findViewById(R.id.addDay);
        addDescription = findViewById(R.id.addDescription);
        addCapacity = findViewById(R.id.addCapacity);
        complete = findViewById(R.id.complete);



        //Edit Texts
        day = findViewById(R.id.Day);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        courseDescription = findViewById(R.id.description);
        courseCapacity = findViewById(R.id.capacity);

        DBHandler myDBHandler = new DBHandler(this);

        //Course
        Course course = AssignMain.courseDetail;

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssignDetails.this, InstructorFunction.class));
            }
        });

        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(day.getText().toString().equals("") || startTime.getText().toString().equals("") || endTime.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter course information",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    myDBHandler.addDayTime(course, String.valueOf(day.getText()), String.valueOf(startTime.getText()), String.valueOf(endTime.getText()));

                    Toast.makeText(getApplicationContext(),
                            "Day and Time added successfully",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

        addCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(courseCapacity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter capacity",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    boolean result = myDBHandler.addCapacity(course, Integer.valueOf(courseCapacity.getText().toString()));
                    if (result == true) {
                        Toast.makeText(getApplicationContext(),
                                "Capacity added successfully",
                                Toast.LENGTH_LONG).show();
                    }

                    else {
                        Toast.makeText(getApplicationContext(),
                                "Invalid Input",
                                Toast.LENGTH_LONG).show();
                    }



                }
            }
        });

        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(courseDescription.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter description",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    //myDBHandler.addDescription(course, courseDescription.getText().toString());

                    Toast.makeText(getApplicationContext(),
                            "Description added successfully",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssignDetails.this, InstructorFunction.class));
            }
        });



    }
}