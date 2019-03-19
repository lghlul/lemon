package com.demo.mapper;

import com.demo.domain.DO.StudentClassDO;
import com.demo.domain.DTO.AddStudentClassDTO;
import com.demo.domain.DTO.GetStudentClassDTO;
import com.demo.domain.DTO.ListStudentClassDTO;
import com.demo.domain.DTO.UpdateStudentClassDTO;

import java.util.List;

public interface StudentClassMapper {

    int saveStudentClass(AddStudentClassDTO addStudentClassDTO);

    int updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO);

    StudentClassDO getStudentClass(GetStudentClassDTO getStudentClassDTO);

    List<ListStudentClassDTO> listStudentClass(ListStudentClassDTO studentClassWithScoreDTO);

}