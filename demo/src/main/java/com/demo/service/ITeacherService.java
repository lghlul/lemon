package com.demo.service;

import com.demo.dto.*;
import com.demo.common.ResultBean;

public interface ITeacherService {

    ResultBean save(AddTeacherDTO addTeacherDTO) throws Exception;

    ResultBean list(ListTeacherDTO listTeacherDTO) throws Exception;

    ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) throws Exception;

    ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) throws Exception;

    ResultBean listByTerm(String teacherNumber) throws Exception;

    ResultBean listByLevel(String teacherNumber) throws Exception;

    ResultBean listWithScore(String teacherNumber) throws Exception;

    ResultBean delete(String teacherNumber) throws Exception;

    ResultBean update(UpdateTeacherDTO updateTeacherDTO) throws Exception;

}
