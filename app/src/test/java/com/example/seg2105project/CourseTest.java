package com.example.seg2105project;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

import java.util.Optional;

public class CourseTest {
    Course course = new Course("tc100", "testcourse");
    Instructor instructor = new Instructor("instructortest","test123");
    Student student = new Student("studenttest","test123");




    @Test
    public void getCourseCode() {
        String actual = course.getCourseCode();
        String expected = "tc100";
        assertEquals("Get Course Code Failed", expected, actual);

    }

    @Test
    public void getCourseName() {

        String actual = course.getCourseName();
        String expected = "testcourse";
        assertEquals("Get Course Name Failed", expected, actual);
    }



    @Test
    public void setAndGetCourseCode() {
        course.setCourseCode("CEG2136");
        String expected = "CEG2136";
        String actual = course.getCourseCode();
        assertEquals("Set and Get Course Code Failed", expected, actual);
    }

    @Test
    public void getInstructorName() {
        String expected = "instructortest";
        String actual = instructor.getUsername();
        assertEquals("Set and Get Course Description Failed", expected, actual);


    }

    @Test
    public void setAndGetInstructorPassword() {
        instructor.setPassword("dummy");
        String expected = "dummy";
        String actual = instructor.getPassword();
        assertEquals("Set and Get Course Capacity Failed", expected, actual);

    }








}