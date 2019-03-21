package com.demo.service;

import com.demo.dto.StudentClazzDTO;
import com.demo.dto.StudentClazzQueryDTO;

import java.util.List;

public interface StudentClazzService {


    StudentClazzDTO saveOrUpdate(StudentClazzDTO studentClazzDTO) throws Exception;

    List<StudentClazzDTO> list(StudentClazzQueryDTO studentClazzQueryDTO) throws Exception;

    boolean checkStudentClazzExist(Integer studentNumber , Integer clazzNumber);

    void delete(Integer studentNumber , Integer clazzNumber) throws Exception;


}
