package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class SignUp extends AppCompatActivity {
    Button signupBtn;
    EditText usernameInput;
    EditText passwordInput;
    RadioButton studentChoiceBtn;
    RadioButton instructorChoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //buttons
        signupBtn = findViewById(R.id.createAccount);
        studentChoiceBtn = findViewById(R.id.studentButton);
        instructorChoiceBtn = findViewById(R.id.instructorButton);

        //inputs
        usernameInput = findViewById(R.id.userName);
        passwordInput = findViewById(R.id.password);



    }
}