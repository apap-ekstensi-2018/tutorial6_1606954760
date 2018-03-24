package com.tutorial.apap.junit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.apap.junit.dao.StudentMapper;
import com.tutorial.apap.junit.model.StudentModel;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;
    
    public StudentServiceDatabase(){
    }
    
    public StudentServiceDatabase(StudentMapper studentMapper){
    		this.studentMapper = studentMapper;
    }


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public boolean addStudent (StudentModel student)
    {
    		log.info ("Student " + student.getNpm() + " added");
        return studentMapper.addStudent (student);
    }

    @Override
    public boolean deleteStudent (String npm)
    {
    		log.info ("Student " + npm + " deleted");
    		return studentMapper.deleteStudent(npm);
    }
    
    @Override
    public boolean updateStudent(String npm, String nama, double gpa)
    {
    		log.info("Student " + npm + " updated");
    		return studentMapper.updateStudent(npm, nama, gpa);
    }

}
