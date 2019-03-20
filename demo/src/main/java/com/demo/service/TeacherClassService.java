package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TeacherClassService {


    TeacherClassDTO saveOrUpdate(TeacherClassDTO teacherClassDTO) throws Exception;

    Object list(ListTeacherClassDTO listTeacherClassDTO) throws Exception;

    boolean checkTeacherClassExist(int teacherClassId);

    void delete(Integer teacherClassId) throws Exception;

}
