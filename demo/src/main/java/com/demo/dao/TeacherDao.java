package com.demo.dao;

import com.demo.dto.TeacherQueryDTO;
import com.demo.model.Teacher;
import com.demo.dto.TeacherDTO;

import java.util.List;

public interface TeacherDao {
    int save(TeacherDTO teacherDTO);

    Teacher get(Integer teacherNumber);

    Teacher getById(String teacherId);

    List<Teacher> list(TeacherQueryDTO teacherDTO);

    int delete(Integer teacherNumber);

    int update(TeacherDTO teacherDTO);


}