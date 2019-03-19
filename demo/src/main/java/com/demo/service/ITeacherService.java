package com.demo.service;

import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Teacher;

public interface ITeacherService {

    ResultBean save(AddTeacherDTO addTeacherDTO) throws Exception;

    ResultBean list(ListTeacherDTO listTeacherDTO) throws Exception;

    ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) throws Exception;

    ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) throws Exception;

    ResultBean listByTerm(String teacherNumber) throws Exception;

    ResultBean listClassByTeacherLevel(String teacherNumber) throws Exception;

    ResultBean listTeacherByTeacherLevel(String teacherNumber) throws Exception;

    ResultBean delete(String teacherNumber) throws Exception;

    ResultBean update(UpdateTeacherDTO updateTeacherDTO) throws Exception;

    Teacher get(String teacherNumber);

    boolean checkTeacherExist(String teacherNumber);

    boolean checkTeacherClassExist(int teacherClassId);

}
