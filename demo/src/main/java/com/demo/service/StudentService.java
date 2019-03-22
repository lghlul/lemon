package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface StudentService {

    List<Student> list(StudentQuery studentQuery) throws Exception;

    PageInfo<Student> listPage(StudentQuery studentQuery) throws Exception;

    void delete(Integer studentId) throws Exception;

    Student read(Integer studentNumber);

    Student readById(String studentId);

    Student saveOrUpdate(Student student) throws Exception;

}
