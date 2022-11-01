package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class EditCourse extends AppCompatActivity {

    EditText courseName, courseCode;
    MyDBHandler dbHandler;
    Button home, findCourse;
    Course cou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);
        home = findViewById(R.id.home);
        findCourse = findViewById(R.id.searchCourse);

        dbHandler = new MyDBHandler(this);

        findCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = dbHandler.findCourse(courseName.getText().toString());

                if (course != null) {
                    cou = course;
                    Intent intent = new Intent(EditCourse.this, EditCourse2.class);
                    intent.putExtra("message_key", cou.getCourseName());
                    startActivity(intent);


                } else {
                    courseName.setText("Not found");
                }



                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditCourse.this, AdminFunction.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });
    }
}