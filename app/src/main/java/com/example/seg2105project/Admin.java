package com.example.seg2105project;
import androidx.appcompat.app.AppCompatActivity;
public class Admin implements User{

    private String username;
    private String password;
    public final String ROLE = "Admin";
    private int id;
    DBHandler myDBHandler = MainActivity.myDBHandler;

    public Admin(){}

    public Admin(String username, String password){
        this.username = username;
        this.password = password;

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

    public boolean deleteUser(User user){
        return myDBHandler.deleteUser(user);
    }

    public void createCourse(Course course){
        myDBHandler.addCourse(course);
    }

    public boolean deleteCourse(Course course){
        return myDBHandler.deleteCourse(course);
    }

    public boolean editCourse(Course oldCourse, Course newCourse){
        return myDBHandler.editCourse(oldCourse, newCourse);
    }

    public String toString(){
        return this.username;
    }



}
