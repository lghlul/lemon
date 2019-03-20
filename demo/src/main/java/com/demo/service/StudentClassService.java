package com.demo.service;

import com.demo.dto.ListStudentClassDTO;
import com.demo.dto.ListStudentDTO;
import com.demo.dto.StudentClassDTO;
import com.demo.dto.StudentDTO;
import com.demo.model.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentClassService {


    StudentClassDTO saveOrUpdate(StudentClassDTO studentClassDTO) throws Exception;

    List<ListStudentClassDTO> list(ListStudentClassDTO studentClassWithScoreDTO) throws Exception;

    boolean checkStudentClassExist(StudentClassDTO studentClassDTO);

    void delete(Integer studentClassId) throws Exception;


}
