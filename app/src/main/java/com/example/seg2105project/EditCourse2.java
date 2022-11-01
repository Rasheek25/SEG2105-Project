package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditCourse2 extends AppCompatActivity {

    EditText courseName, courseCode;
    MyDBHandler dbHandler;
    Button home, editCourse;
    Course cou;
    String receive_msg = "";
    TextView name, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course2);
        Intent intent = getIntent();
        receive_msg = intent.getStringExtra("message_key");


        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);
        home = findViewById(R.id.home);
        editCourse = findViewById(R.id.edit);
        name = findViewById(R.id.searchName);
        code = findViewById(R.id.searchCode);

        dbHandler = new MyDBHandler(this);

        cou = dbHandler.findCourse(receive_msg);

        name.setText(cou.getCourseName());
        code.setText(cou.getCourseCode());

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditCourse2.this, AdminFunction.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couName = cou.getCourseName();
                String newName = courseName.getText().toString();
                String newCode = courseCode.getText().toString();

                dbHandler.deleteCourse(couName);
                Course course = new Course(newName, newCode);
                dbHandler.addCourse(course);
                courseName.setText("");
                courseCode.setText("");
                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });


    }
}