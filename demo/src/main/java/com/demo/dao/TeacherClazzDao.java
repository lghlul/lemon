package com.demo.dao;

import com.demo.model.TeacherClazz;
import com.demo.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClazzDao {

    int save(TeacherClazzDTO teacherClazzDTO);

    int update(TeacherClazzDTO teacherClazzDTO);

    TeacherClazz get(@Param("teacherNumber") int teacherNumber ,@Param("clazzNumber") int clazzNumber);

    List<TeacherClazzDTO> list(TeacherClazzQueryDTO teacherClazzQueryDTO);

    List<TeacherClazzTermQueryDTO> listClazz(@Param("teacherNumber") Integer teacherNumber);

    List<TeacherClazzScoreQueryDTO> listTeacherByTeacherLevel();

    int delete(@Param("teacherNumber") int teacherNumber ,@Param("clazzNumber") int clazzNumber);

}