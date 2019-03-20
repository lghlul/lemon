package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStudentService {

    PageInfo<Student> list(ListStudentDTO studentDTO) throws Exception;


    StudentClassDTO saveOrUpdateStudentClass(StudentClassDTO studentClassDTO) throws Exception;

    List<ListStudentClassDTO> listStudentClass(ListStudentClassDTO studentClassWithScoreDTO) throws Exception;

    void delete(Integer studentId) throws Exception;

    Student get(StudentDTO studentDTO);

    boolean checkStudentExist(StudentDTO studentDTO);

    boolean checkStudentClassExist(StudentClassDTO studentClassDTO);

    StudentDTO saveOrUpdate(StudentDTO studentDTO) throws Exception;

}
