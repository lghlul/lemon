package com.demo.dao;

import com.demo.model.Student;
import com.demo.dto.StudentDTO;
import com.demo.dto.ListStudentDTO;

import java.util.List;

public interface StudentDao {

    int save(StudentDTO studentDTO);

    Student get(StudentDTO studentDTO);

    List<Student> list(ListStudentDTO studentDTO);

    int delete(int studentId);

    int update(StudentDTO studentDTO);


}