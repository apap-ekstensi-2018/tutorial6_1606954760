package com.tutorial.apap.junit.service;

import java.util.List;

import com.tutorial.apap.junit.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);


    List<StudentModel> selectAllStudents ();


    boolean addStudent (StudentModel student);


    boolean deleteStudent (String npm);


	boolean updateStudent(String npm, String nama, double gpa);
}
