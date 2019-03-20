package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Student;
import com.github.pagehelper.PageInfo;


public interface StudentService {

    PageInfo<Student> list(ListStudentDTO studentDTO) throws Exception;

    void delete(Integer studentId) throws Exception;

    Student get(StudentDTO studentDTO);

    boolean checkStudentExist(StudentDTO studentDTO);

    StudentDTO saveOrUpdate(StudentDTO studentDTO) throws Exception;

}
