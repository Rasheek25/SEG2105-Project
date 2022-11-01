package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminFunction extends AppCompatActivity {

    TextView welcome;
    String reciever_msg = "";
    Button logOut, createCourse, editCourse, deleteCourse, deleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_function);

        welcome = (TextView) findViewById(R.id.welcomeUser);
        Intent intent = getIntent();
        reciever_msg = intent.getStringExtra("message_key");
        welcome.setText("Welcome " + reciever_msg);

        logOut = findViewById(R.id.logOut);
        createCourse = findViewById(R.id.createCourse);
        editCourse = findViewById(R.id.editCourse);
        deleteCourse = findViewById(R.id.deleteCourse);
        deleteAccount = findViewById(R.id.deleteAcount);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminFunction.this, MainActivity.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminFunction.this, CreateCourse.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminFunction.this, DeleteCourse.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminFunction.this, EditCourse.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminFunction.this, DeleteAccount.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });
    }
}