package com.example.seg2105project;

import static androidx.core.content.ContextCompat.startActivity;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.test.rule.ActivityTestRule;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    Course course = new Course("tc12", "testcourse");
    Instructor instructor = new Instructor("instructortest","test123");
    Student student = new Student("studenttest","test123");
    DBHandler dbHandler = new DBHandler(InstrumentationRegistry.getInstrumentation().getTargetContext());


    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.seg2105project", appContext.getPackageName());
    }*/


    @Test
    public void getCourseCode() {
        String actual = course.getCourseCode();
        String expected = "tc12";
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
        dbHandler.addCourse(course);
        enroll(course);
        assertTrue(isEnrolled(course));

    }


    @Test
    public void dropTest(){
        drop(course);
        boolean test = isEnrolled(course);
        assertEquals(false, test);
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
        Course test = dbHandler.findByCourseName(course.getCourseName());
        assertEquals(course.getCourseName(), test.getCourseName());
        assertEquals(course.getCourseCode(), test.getCourseCode());

    }

    @Test
    public void findByCourseDay(){
        dbHandler.editCourseSchedule(course, "Friday : 10:00 AM - 11:30 AM | ");
        String schedule = dbHandler.getCourseSchedule(course);
        assertEquals("Friday : 10:00 AM - 11:30 AM | ", schedule);
        boolean output = FindViewByCourseDay("Friday");
        assertTrue(output);
    }







    //imported method since we couldn't start activity from tests so that we can use their methods
    public void enroll(Course course) {
        StringBuffer buffer;
        //get course str from database
        student.courses = dbHandler.getStudentCourses(student);

        if (student.courses.equals("N/A")){
            student.courses = course.toString() + ";";
            dbHandler.editStudentCourses(student, student.courses);//update database

        }

        else{
            buffer = new StringBuffer(student.courses);
            //constructing student list str
            buffer.append(course.toString());
            buffer.append(";");
            student.courses = buffer.toString();
            dbHandler.editStudentCourses(student, student.courses);//update database
        }

    }

    public boolean isEnrolled(Course course){
        //update
        student.courses = dbHandler.getStudentCourses(student);
        if(student.courses.equals("N/A")){
            return false;
        }
        String[] coursesArray = student.courses.split(";");
        for (String c : coursesArray){
            if( c.equals(course.toString())){
                return true;
            }

        }
        return false;
    }

    public void drop(Course course) {
        student.courses = dbHandler.getStudentCourses(student);
        String[] coursesArray = student.courses.split(";");
        List<String> temp = Arrays.asList(coursesArray);
        LinkedList<String> coursesLinkedList = new LinkedList<String>(temp);
        for (String c: coursesLinkedList){
            if( c.equals(course.toString())){
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
        dbHandler.editUserCourses(student, buffer.toString());

    }

    public boolean FindViewByCourseDay(String day) {

        boolean found = false;
        Cursor cursor = dbHandler.getCourses();
        if (cursor.getCount() == 0) {
            return false;
        }

        else {
            while (cursor.moveToNext()) {
                List<String> courseDays = new ArrayList<>();

                //filtering the course days from the schedule
                String[] days = cursor.getString(6).split(" | ");
                for (String d : days ){
                    String[] temp = d.split(" : ");
                    courseDays.add(temp[0]);
                }

                for(String g : courseDays){
                    if(day.equalsIgnoreCase(g)){
                        found = true;

                    }
                }
            }
        }



        return found;

    }
}