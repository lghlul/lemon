package com.demo.mapper;

import com.demo.domain.DO.StudentDO;
import com.demo.domain.DTO.AddStudentDTO;
import com.demo.domain.DTO.ListStudentDTO;

import java.util.List;

public interface StudentMapper {

    int saveStudent(AddStudentDTO addStudentDTO);

    StudentDO getStudent(String studentNumber);

    List<StudentDO> listStudent(ListStudentDTO studentDTO);

    int countStudent(ListStudentDTO studentDTO);

}