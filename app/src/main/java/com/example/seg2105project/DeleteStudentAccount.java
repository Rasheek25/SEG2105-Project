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

public class DeleteStudentAccount extends AppCompatActivity {

    Button searchAccountBtn, homeBtn, deleteAccountBtn;

    TextView usernameText;
    EditText usernameTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_account);

        homeBtn = findViewById(R.id.home);
        searchAccountBtn = findViewById(R.id.searchAccount);
        deleteAccountBtn = findViewById(R.id.deleteAccount);
        usernameText = findViewById(R.id.usernameText);
        usernameTE = findViewById(R.id.userName);
        Admin admin = new Admin();
        DBHandler myDBHandler = new DBHandler(this);

        //home button
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteStudentAccount.this, AdminFunction.class));
            }
        });


        searchAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //delete button clicked without entering username
                deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),
                                "Please enter username.",
                                Toast.LENGTH_LONG).show();
                    }
                });

                if(myDBHandler.UserFound(usernameTE.getText().toString())){
                    usernameText.setText(usernameTE.getText());

                    deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           if(admin.deleteUser(new Student(usernameTE.getText().toString()))){
                               Toast.makeText(getApplicationContext(),
                                       "Account deleted successfully",
                                       Toast.LENGTH_LONG).show();
                           }
                           else if(usernameTE.getText().equals("")){
                               Toast.makeText(getApplicationContext(),
                                       "Please enter username.",
                                       Toast.LENGTH_LONG).show();
                           }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "User not found. Please try again.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}