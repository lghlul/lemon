package com.demo.dao;

import com.demo.dto.TeacherQuery;
import com.demo.model.Teacher;

import java.util.List;

public interface TeacherDao {
    int save(Teacher teacher);

    Teacher read(Integer teacherNum);

    Teacher readById(String teacherId);

    List<Teacher> list(TeacherQuery teacherQuery);

    int delete(Integer teacherNum);

    int update(Teacher teacher);


}