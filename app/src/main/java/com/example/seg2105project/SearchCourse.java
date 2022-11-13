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

import java.util.ArrayList;

public class SearchCourse extends AppCompatActivity {

    EditText courseCodeET, courseNameET;
    Button searchCourseBtn;
    ListView courseListView;

    ArrayList<String> courseList;
    ArrayAdapter adapter;
    DBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);
        Admin admin = new Admin();
        myDBHandler = new DBHandler(this);
        courseCodeET = findViewById(R.id.courseCode);
        courseNameET = findViewById(R.id.courseName);
        searchCourseBtn = findViewById(R.id.searchCourse);
        courseList = new ArrayList<>();



        viewCourses();
    }

    private void viewCourses() {
        courseList.clear();
        Cursor cursor = myDBHandler.getCourses();
        if (cursor.getCount() == 0) {
            Toast.makeText(SearchCourse.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                courseList.add(cursor.getString(0) + ' ' + cursor.getString(1) + ' ' + cursor.getString(2));

            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);
    }

    private void FindViewProducts1(Product product) {
        productList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                if (product.getProductName().equals(cursor.getString(1)) && product.getProductPrice() == Double.parseDouble(cursor.getString(2))) {
                    productList.add(cursor.getString(0) + ' ' + cursor.getString(1) + ' ' + cursor.getString(2));
                }
            }
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);

    }

    private void FindViewProducts2(Product product) {
        productList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                if (product.getProductPrice() == Double.parseDouble(cursor.getString(2))) {
                    productList.add(cursor.getString(0) + ' ' + cursor.getString(1) + ' ' + cursor.getString(2));
                }
            }
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);

    }

    private void FindViewProducts3(Product product) {
        productList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }

        else {
            while (cursor.moveToNext()) {
                if (product.getProductName().equals(cursor.getString(1))) {
                    productList.add(cursor.getString(0) + ' ' + cursor.getString(1) + ' ' + cursor.getString(2));
                }
            }
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);

    }




}
