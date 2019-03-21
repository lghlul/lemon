package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Teacher;


public interface TeacherService {

    Object list(TeacherQueryDTO listTeacherDTO) throws Exception;

    void delete(Integer teacherNumber) throws Exception;

    TeacherDTO saveOrUpdate(TeacherDTO teacherDTO) throws Exception;

    Teacher read(Integer teacherNumber);

    Teacher readById(String teacherId);

    boolean checkTeacherNumberExist(Integer teacherNumber);
}
