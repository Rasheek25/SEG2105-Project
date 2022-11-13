package com.example.seg2105project;

public class Student implements User {
    private String username;
    private String password;
    public final String ROLE = "Student";
    private int id;

    public Student(){}

    public Student(String username, String password){
        this.username = username;
        this.password = password;
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
}
