package com.example.seg2105project;

public class Course {

    String courseCode;
    String courseName;
    Instructor instructor;
    Integer studentCapacity;
    String courseDescription;
    String courseSchedule;
    DBHandler myDBHandler = MainActivity.myDBHandler;


    public Course(String courseCode, String courseName){
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public Course(){
        this.courseCode = "";
        this.courseName = "";
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
        myDBHandler.editCourseInstructor(this, "");
        this.courseDescription = null;
        myDBHandler.editCourseDescription(this, "");
        this.studentCapacity = null;
        myDBHandler.editCourseCapacity(this, 0);
        this.courseSchedule = null;
        myDBHandler.editCourseSchedule(this, "");

    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public String getInstructorName(){
        return this.instructor.getUsername();
    }

    public Integer getStudentCapacity() {
        return this.studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
        myDBHandler.editCourseCapacity(this, studentCapacity);
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        myDBHandler.editCourseDescription(this, courseDescription);
    }

    public String getCourseDescription() {
        return this.courseDescription;
    }

    public String getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(String courseSchedule) {
        this.courseSchedule = courseSchedule;
        myDBHandler.editCourseSchedule(this, courseSchedule);
    }


}
