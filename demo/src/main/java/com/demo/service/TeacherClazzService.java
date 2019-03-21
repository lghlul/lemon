package com.demo.service;

import com.demo.dto.*;

import java.util.List;

public interface TeacherClazzService {


    TeacherClazzDTO saveOrUpdate(TeacherClazzDTO teacherClazzDTO) throws Exception;

    Object list(TeacherClazzQueryDTO teacherClazzQueryDTO) throws Exception;

    boolean checkTeacherClazzExist(Integer teacherNumber , Integer clazzNumber);

    void delete(Integer teacherNumber , Integer clazzNumber) throws Exception;

    List<TeacherClazzTermQueryDTO> listByTerm(Integer teacherNumber) throws Exception;

    List<TeacherClazzTermQueryDTO> listClazzByTeacherLevel() throws Exception;

    List<TeacherClazzScoreQueryDTO> listTeacherByTeacherLevel() throws Exception;

}
