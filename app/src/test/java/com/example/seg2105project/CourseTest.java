package com.example.seg2105project;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

public class CourseTest {
    Course course = new Course("tc100", "testcourse");
    Instructor instructor = new Instructor("instructortest","test123");
    Student student = new Student("studenttest","test123");
    DBHandler dbHandler = new DBHandler(MainActivity);



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





    @Test
    public void enrollTest() {
        dbHandler.addUser(student);
        student.enroll(course);
        assertTrue(student.isEnrolled(course));
    }


    @Test
    public void dropTest(){
        student.drop(course);
        assertFalse(student.isEnrolled(course));
    }

    @Test
    public void findByCourseCodeTest(){
        dbHandler.addCourse(course);
        Course test = dbHandler.findByCourseCode(course.getCourseCode());
        assertEquals(course.getCourseCode(), test.getCourseCode());
        assertEquals(course.getCourseName(), test.getCourseName());
    }

    @Test
    public void findByCourseNameTest(){
        Course test = dbHandler.findByCourseCode(course.getCourseName());
        assertEquals(course.getCourseName(), test.getCourseName());
        assertEquals(course.getCourseCode(), test.getCourseCode());

    }

    @Test
    public void findByCourseDay(){
        course.setCourseSchedule("Tuesday : 10:00 AM - 11:30 AM | ");
        String schedule = dbHandler.getCourseSchedule(course);
        assertEquals(course.getCourseSchedule(), schedule);
    }
}