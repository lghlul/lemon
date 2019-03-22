package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface TeacherService {

    List<Teacher> list(TeacherQuery teacherQuery) throws Exception;

    PageInfo<Teacher> listPage(TeacherQuery teacherQuery) throws Exception;

    void delete(Integer teacherNumber) throws Exception;

    Teacher saveOrUpdate(Teacher teacher) throws Exception;

    Teacher read(Integer teacherNumber);

    Teacher readById(String teacherId);

}
