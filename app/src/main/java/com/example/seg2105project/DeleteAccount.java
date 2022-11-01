package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteAccount extends AppCompatActivity {

    EditText accountName, role;
    MyDBHandlerAccounts dbHandler;
    Button deleteAccount, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        accountName = findViewById(R.id.userName);
        role = findViewById(R.id.accountRole);

        deleteAccount = findViewById(R.id.DeleteAccount);
        home = findViewById(R.id.home);

        dbHandler = new MyDBHandlerAccounts(this);

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHandler.deleteAccount(accountName.getText().toString());


                if (result) {
                    accountName.setText("Record deleted");

                }
                else {
                    accountName.setText("No Match Found");
                }



                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteAccount.this, AdminFunction.class));

                //  Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }

        });

    }
}