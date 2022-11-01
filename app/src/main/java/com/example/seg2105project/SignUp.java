package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignUp extends AppCompatActivity {

    EditText userName, password;
    MyDBHandlerAccounts dbHandler;
    Button createAccount;
    String accountRole = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        createAccount = findViewById(R.id.createAccount);

        dbHandler = new MyDBHandlerAccounts(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = userName.getText().toString();
                String Password = password.getText().toString();

                Account account = new Account(Username, Password, accountRole);
                dbHandler.addAccount(account);

                startActivity(new Intent(SignUp.this, MainActivity.class));
                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void RadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.instructorButton:
                if (checked)
                    accountRole = "Instructor";
                break;
            case R.id.studentButton:
                if (checked)
                    accountRole = "Student";
                break;
        }
    }
}