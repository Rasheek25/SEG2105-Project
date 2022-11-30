package com.example.seg2105project;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Student implements User {
    private String username;
    private String password;
    public final String ROLE = "Student";
    private int id;
    public String courses;
    DBHandler myDBHandler = MainActivity.myDBHandler;

    public static String courseConflictErrorMsg;

    public Student(){}

    public Student(String username, String password){
        this.username = username;
        this.password = password;
        this.courses = "N/A";
    }
    public Student(String username){
        this.username = username;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    public String toString(){
        return this.username;
    }

    public void enroll(Course course) {
        StringBuffer buffer;
        //get course str from database
        this.courses = myDBHandler.getStudentCourses(this);

        if (this.courses.equals("N/A")){
            this.courses = course.toString() + ";";
            myDBHandler.editStudentCourses(this, this.courses);//update database
            course.addStudent(this);//sync
        }

        else{
            buffer = new StringBuffer(this.courses);
            //constructing student list str
            buffer.append(course.toString());
            buffer.append(";");
            this.courses = buffer.toString();
            myDBHandler.editStudentCourses(this, this.courses);//update database
            course.addStudent(this);//sync
        }

    }

    public void drop(Course course) {
        this.courses = myDBHandler.getStudentCourses(this);
        String[] coursesArray = this.courses.split(";");
        List<String> temp = Arrays.asList(coursesArray);
        LinkedList<String> coursesLinkedList = new LinkedList<String>(temp);
        for (String c: coursesLinkedList){
            if( c.equals(course.toString())){
                //coursesArrayList.remove(coursesArrayList.indexOf(c));
                coursesLinkedList.remove(c);
            }
        }
        StringBuffer buffer = new StringBuffer();
        coursesLinkedList.remove("");
        for (String c: coursesLinkedList){
            buffer.append(c);
            buffer.append(";");
        }
        //update database
        myDBHandler.editUserCourses(this, buffer.toString());

        //sync with course
        course.removeStudent(this);

    }

    public boolean isEnrolled(Course course){
        //update
        this.courses = myDBHandler.getStudentCourses(this);
        if(this.courses.equals("N/A")){
            return false;
        }
        String[] coursesArray = this.courses.split(";");
        for (String c : coursesArray){
            if( c.equals(course.toString())){
                return true;
            }

        }
        return false;
    }

    //returns true if check is passed
    public boolean checkStudentCoursesConflict(Course newCourse){
        //resetting error msg
        courseConflictErrorMsg = null;
        String coursesStr = myDBHandler.getStudentCourses(this);

        //student have not enrolled to any courses yet, or not enrolled to any course
        if(coursesStr.equals("N/A") || coursesStr.equals("")){
            return true;
        }

        //constructing course list
        String[] coursesArr = coursesStr.split(";");
        List<String> coursesStrList = Arrays.asList(coursesArr);
        List<Course> coursesList = new ArrayList<>();
        coursesStrList.remove("");
        for(String d: coursesStrList){
            coursesList.add(new Course(d));

        }
        Log.d("CREATION",coursesList.toString());

        //checking course to course conflict
        for(Course c : coursesList){
            if(c.courseConflict(newCourse)){
                courseConflictErrorMsg = "Schedule conflict with " + c.toString();
                return false;
            }
        }

        //all conditions checked
        return true;
    }

}
