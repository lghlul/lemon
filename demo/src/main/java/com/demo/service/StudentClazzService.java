package com.demo.service;

import com.demo.dto.StudentClazzQuery;
import com.demo.model.StudentClazz;

import java.util.List;

public interface StudentClazzService {


    StudentClazz saveOrUpdate(StudentClazz studentClazz) throws Exception;

    List<StudentClazz> list(StudentClazzQuery studentClazzQuery) throws Exception;

    boolean checkStudentClazzExist(Integer studentNumber, Integer clazzNumber);

    void delete(Integer studentNumber, Integer clazzNumber) throws Exception;


}
