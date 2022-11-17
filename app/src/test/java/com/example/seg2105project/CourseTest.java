package com.example.seg2105project;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseTest {
    Course course = new Course("SEG2105", "Software");
    Instructor instructor = new Instructor("test","test123");

    @Test
    public void getCourseCode() {
        String actual = course.getCourseCode();
        String expected = "SEG2105";
        assertEquals("Get Course Code Failed", expected, actual);

    }

    @Test
    public void getCourseName() {

        String actual = course.getCourseName();
        String expected = "Software";
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
        String expected = "test";
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