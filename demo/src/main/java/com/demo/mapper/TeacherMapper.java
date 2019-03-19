package com.demo.mapper;

import com.demo.dto.UpdateStudentDTO;
import com.demo.dto.UpdateTeacherDTO;
import com.demo.model.Teacher;
import com.demo.dto.AddTeacherDTO;
import com.demo.dto.ListTeacherDTO;

import java.util.List;

public interface TeacherMapper {
    int save(AddTeacherDTO addTeacherDTO);

    Teacher get(String teacherNumber);

    List<Teacher> list(ListTeacherDTO teacherDTO);

    int count(ListTeacherDTO teacherDTO);

    int delete(String teacherNumber);

    int update(UpdateTeacherDTO updateTeacherDTO);


}