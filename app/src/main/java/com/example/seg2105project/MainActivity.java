package com.example.seg2105project;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText userName, password;
    Button login, signUp;
    MyDBHandlerAccounts dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);

        dbHandler = new MyDBHandlerAccounts(this);

        Account acc = new Account("admin", "admin123", "Admin");

        dbHandler.addAccount(acc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = dbHandler.findAccount(userName.getText().toString());

                if (account != null) {

                    if (account.getAccountRole().equals("Admin")) {

                        Intent intent = new Intent(MainActivity.this, AdminFunction.class);
                        intent.putExtra("message_key", userName.getText().toString());
                        startActivity(intent);

                    }

                    else if (account.getAccountRole().equals("Student")) {
                        Intent intent = new Intent(MainActivity.this, StudentFunction.class);
                        intent.putExtra("message_key", userName.getText().toString());
                        startActivity(intent);
                    }

                    else if (account.getAccountRole().equals("Instructor")) {
                        Intent intent = new Intent(MainActivity.this, InstructorFunction.class);
                        intent.putExtra("message_key", userName.getText().toString());
                        startActivity(intent);
                    }

                } else {
                    password.setText("No Match Found");
                }

//                Toast.makeText(MainActivity.this, "Add product", Toast.LENGTH_SHORT).show();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });
    }
}