package com.example.seg2105project;

public class Instructor implements User {
    private String username;
    private String password;
    public final String ROLE = "Instructor";
    private int id;

    public Instructor(){}

    public Instructor(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Instructor(String username){
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

    public void editCourseDescription(Course course, String description){
        course.setCourseDescription(description);
    }
    public void editCourseSchedule(Course course, String schedule){
        course.setCourseSchedule(schedule);
    }
    public void editCourseCapacity(Course course, Integer capacity){
        course.setStudentCapacity(capacity);
    }

}
