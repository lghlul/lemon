package com.demo.dao;

import com.demo.dto.StudentQueryDTO;
import com.demo.model.Student;
import com.demo.dto.StudentDTO;

import java.util.List;

public interface StudentDao {

    int save(StudentDTO studentDTO);

    Student get(int studentNumber);

    Student getById(String studentId);

    List<Student> list(StudentQueryDTO studentQueryDTO);

    int delete(int studentNumber);

    int update(StudentDTO studentDTO);


}