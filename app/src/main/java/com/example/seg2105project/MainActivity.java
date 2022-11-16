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

public class MainActivity extends AppCompatActivity {

    Button loginBtn, signupBtn;
    EditText usernameET, passwordET;
    static User currentUser;
    static Admin admin;
    static DBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentUser = null;
        admin = new Admin();
        myDBHandler = new DBHandler(this);


        //precreated admin user
       if(!myDBHandler.UserFound(new Admin("admin", "admin123"))) {
            User admn = new Admin("admin", "admin123");
            myDBHandler.addUser(admn);
       }



        //buttons
        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signUp);

        //inputs
        usernameET = findViewById(R.id.userName);
        passwordET = findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = usernameET.getText().toString();
                String enteredpassword = passwordET.getText().toString();
                if(myDBHandler.UserFound(enteredUsername, enteredpassword)) {
                    //if user is student
                    if((myDBHandler.getUserRole(enteredUsername, enteredUsername)).equals("Student")) {
                        currentUser = new Student(enteredUsername, enteredpassword);
                        startActivity(new Intent(MainActivity.this, StudentFunction.class));
                    }
                    //if user is instructor
                    else if(myDBHandler.getUserRole(enteredUsername, enteredUsername).equals("Instructor")) {
                        currentUser = new Instructor(enteredUsername, enteredpassword);
                        startActivity(new Intent(MainActivity.this, InstructorFunction.class));
                    }
                    //if user is admin
                    else if(myDBHandler.getUserRole(enteredUsername, enteredUsername).equals("Admin")) {
                        currentUser = new Admin(enteredUsername, enteredpassword);
                        startActivity(new Intent(MainActivity.this, AdminFunction.class));
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "User not found. Please try again.",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

    }

}