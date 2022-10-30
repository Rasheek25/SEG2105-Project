package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    Button loginBtn, signupBtn;
    EditText usernameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons
        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signUp);

        //inputs
        usernameET = findViewById(R.id.userName);
        passwordET = findViewById(R.id.password);


    }
}