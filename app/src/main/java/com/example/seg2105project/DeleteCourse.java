package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeleteCourse extends AppCompatActivity {

    EditText courseName, courseCode;
    MyDBHandler dbHandler;
    Button deleteCourse, home, findCourse;
    TextView name, code;
    Course c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);

        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);
        deleteCourse = findViewById(R.id.delete);
        home = findViewById(R.id.home);
        name = findViewById(R.id.searchName);
        code = findViewById(R.id.searchCode);
        findCourse = findViewById(R.id.searchCourse);

        dbHandler = new MyDBHandler(this);



        findCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = dbHandler.findCourse(courseName.getText().toString());

                if (course != null) {
                    c = course;
                    name.setText(String.valueOf(course.getCourseName()));
                    code.setText(String.valueOf(course.getCourseCode()));

                } else {
                    name.setText("Not found");
                }



                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteCourse.this, AdminFunction.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHandler.deleteCourse(c.getCourseName());

                if (result) {
                    name.setText("Record deleted");

                }
                else {
                    name.setText("No Match Found");
                }



                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });
    }
}