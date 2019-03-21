package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Student;


public interface StudentService {

    Object list(StudentQueryDTO studentDTO) throws Exception;

    void delete(Integer studentId) throws Exception;

    Student read(Integer studentNumber);

    Student readById(String studentId);

    boolean checkStudentNumberExist(Integer studentNumber);

    StudentDTO saveOrUpdate(StudentDTO studentDTO) throws Exception;

}
