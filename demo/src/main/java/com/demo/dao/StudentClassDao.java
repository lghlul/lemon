package com.demo.dao;

import com.demo.model.StudentClass;
import com.demo.dto.StudentClassDTO;
import com.demo.dto.ListStudentClassDTO;

import java.util.List;

public interface StudentClassDao {

    int save(StudentClassDTO studentClassDTO);

    int update(StudentClassDTO studentClassDTO);

    StudentClass get(StudentClassDTO studentClassDTO);

    List<ListStudentClassDTO> list(ListStudentClassDTO studentClassWithScoreDTO);

}