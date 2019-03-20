package com.demo.dao;

import com.demo.model.Teacher;
import com.demo.dto.TeacherDTO;
import com.demo.dto.ListTeacherDTO;

import java.util.List;

public interface TeacherDao {
    int save(TeacherDTO teacherDTO);

    Teacher get(TeacherDTO teacherDTO);

    List<Teacher> list(ListTeacherDTO teacherDTO);

    int delete(Integer teacherId);

    int update(TeacherDTO teacherDTO);


}