package com.demo.service;

import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;

public interface ITeacherService {

    ResultBean addTeacher(AddTeacherDTO addTeacherDTO);

    ResultBean listTeacher(ListTeacherDTO listTeacherDTO);


    ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO);


    ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO);

    ResultBean listTeacherClassByTerm(String teacherNumber);

    ResultBean listTeacherClassByTermWithLevel(String teacherNumber);

    ResultBean listTeacherClassScore(String teacherNumber);
}
