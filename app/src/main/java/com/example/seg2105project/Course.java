package com.example.seg2105project;

public class Course {

    String courseCode;
    String courseName;
    Instructor instructor;
    Integer studentCapacity;
    String courseDescription;


    public Course(String courseCode, String courseName){
        this.courseCode = courseCode;
        this.courseName = courseName;
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

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public Integer getStudentCapacity() {
        return this.studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDescription() {
        return this.courseDescription;
    }
}
