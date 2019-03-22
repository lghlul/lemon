package com.demo.dao;

import com.demo.model.ClazzTermReport;
import com.demo.model.TeacherClazz;
import com.demo.dto.*;
import com.demo.model.TeacherClazzReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClazzDao {

    int save(TeacherClazz teacherClazz);

    int update(TeacherClazz teacherClazz);

    List<TeacherClazz> list(TeacherClazzQuery teacherClazzQuery);

    List<ClazzTermReport> listClazz(@Param("teacherNumber") Integer teacherNumber);

    List<TeacherClazzReport> listTeacherByTeacherLevel();

    int delete(@Param("teacherNumber") Integer teacherNumber, @Param("clazzNumber") Integer clazzNumber);

}