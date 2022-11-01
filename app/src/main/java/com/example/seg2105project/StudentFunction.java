package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class StudentFunction extends AppCompatActivity {

    TextView welcome;
    String reciever_msg = "";
    Button logOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_function);

        welcome = (TextView) findViewById(R.id.welcomeUser);
        Intent intent = getIntent();
        reciever_msg = intent.getStringExtra("message_key");
        welcome.setText("Welcome " + reciever_msg);

        logOut = findViewById(R.id.logOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentFunction.this, MainActivity.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });


    }
}