package com.example.seg2105project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {

    String courseCode;
    String courseName;
    Instructor instructor;
    Integer studentCapacity;
    String courseDescription;
    String courseSchedule;
    String enrolledStudents;
    Integer studentCount;
    DBHandler myDBHandler = MainActivity.myDBHandler;


    public Course(String courseCode, String courseName){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDescription = "N/A";
        this.instructor = null;
        this.studentCapacity = 0;
        this.studentCount = 0;
        this.courseSchedule = "N/A";
        this.enrolledStudents = "N/A"; {
        };
    }

    public Course(){
        this.courseCode = "";
        this.courseName = "";
        this.courseDescription = "N/A";
        this.instructor = null;
        this.studentCapacity = 0;
        this.studentCount = 0;
        this.courseSchedule = "N/A";
        this.enrolledStudents = "N/A";

    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }


    public void assign(Instructor instructor) {
        this.instructor = instructor;
        myDBHandler.editCourseInstructor(this, instructor.getUsername());
    }

    public void unassign(){
        this.instructor = null;
        myDBHandler.editCourseInstructor(this, "N/A");
        this.courseDescription = "N/A";
        myDBHandler.editCourseDescription(this, "N/A");
        this.studentCapacity = 0;
        myDBHandler.editCourseCapacity(this, 0);
        this.courseSchedule = "N/A";
        myDBHandler.editCourseSchedule(this, "N/A");

    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public String getInstructorName(){
       return myDBHandler.getCourseInstructor(this);
    }

    public Integer getStudentCapacity() {
        return myDBHandler.getCourseCapacity(this);
    }

    public Integer getStudentCount(){ return myDBHandler.getStudentCount(this);}

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
        myDBHandler.editCourseCapacity(this, studentCapacity);
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        myDBHandler.editCourseDescription(this, courseDescription);
    }

    public void setStudentCount(Integer newCount) {
        this.studentCount = newCount;
        myDBHandler.editCourseCount(this, newCount);
    }


    public void addStudent(Student student) {
        StringBuffer buffer;
        //get student str from database
        this.enrolledStudents = myDBHandler.getEnrolledStudents(this);

        if (this.enrolledStudents.equals("N/A")){
            this.enrolledStudents = student.toString() + ";";
            myDBHandler.editEnrolledStudents(this, this.enrolledStudents);
        }

        else{
            buffer = new StringBuffer(this.enrolledStudents);
            //constructing student list str
            buffer.append(student.toString());
            buffer.append(";");
            this.enrolledStudents = buffer.toString();
            myDBHandler.editEnrolledStudents(this, this.enrolledStudents);
        }

    }

    public String getCourseDescription() {
        return myDBHandler.getCourseDescription(this);
    }

    public String getCourseSchedule() {
        return myDBHandler.getCourseSchedule(this);
    }

    public void setCourseSchedule(String courseSchedule) {
        this.courseSchedule = courseSchedule;
        myDBHandler.editCourseSchedule(this, courseSchedule);
    }

    public String toString(){
        return this.courseCode + " : " + this.courseName;
    }


    public void removeStudent(Student student) {
        this.enrolledStudents = myDBHandler.getEnrolledStudents(this);
        String[] studentsArray = this.enrolledStudents.split(";");
        List<String> studentsArrayList = Arrays.asList(studentsArray);
        for (String c: studentsArrayList){
            if( c.equals(student.toString())){
                studentsArrayList.remove(c);
            }
        }
        StringBuffer buffer = new StringBuffer();
        studentsArrayList.remove("");
        for (String c: studentsArrayList){
            buffer.append(c);
            buffer.append(";");
        }
        //update database
        myDBHandler.editEnrolledStudents(this, buffer.toString());

    }
}
