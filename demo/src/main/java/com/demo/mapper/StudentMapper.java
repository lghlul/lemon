package com.demo.mapper;

import com.demo.dto.UpdateStudentDTO;
import com.demo.model.Student;
import com.demo.dto.AddStudentDTO;
import com.demo.dto.ListStudentDTO;

import java.util.List;

public interface StudentMapper {

    int save(AddStudentDTO addStudentDTO);

    Student get(String studentNumber);

    List<Student> list(ListStudentDTO studentDTO);

    int count(ListStudentDTO studentDTO);

    int delete(String studentNumber);

    int update(UpdateStudentDTO updateStudentDTO);

}