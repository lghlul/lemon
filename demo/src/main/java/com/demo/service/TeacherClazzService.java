package com.demo.service;

import com.demo.dto.*;
import com.demo.model.ClazzTermReport;
import com.demo.model.TeacherClazz;
import com.demo.model.TeacherClazzReport;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TeacherClazzService {


    TeacherClazz saveOrUpdate(TeacherClazz teacherClazz) throws Exception;

    List<TeacherClazz> list(TeacherClazzQuery teacherClazzQuery) throws Exception;

    PageInfo<TeacherClazz> listPage(TeacherClazzQuery teacherClazzQuery) throws Exception;

    void delete(Integer teacherNumber, Integer clazzNumber) throws Exception;

    List<ClazzTermReport> listByTerm(Integer teacherNumber) throws Exception;

    List<ClazzTermReport> listClazzByTeacherLevel() throws Exception;

    List<TeacherClazzReport> listTeacherByTeacherLevel() throws Exception;

}
