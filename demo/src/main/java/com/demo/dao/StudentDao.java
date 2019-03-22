package com.demo.dao;

import com.demo.dto.StudentQuery;
import com.demo.model.Student;

import java.util.List;

public interface StudentDao {

    int save(Student student);

    Student read(int studentNum);

    Student readById(String studentId);

    List<Student> list(StudentQuery studentQuery);

    int delete(int studentNum);

    int update(Student student);


}