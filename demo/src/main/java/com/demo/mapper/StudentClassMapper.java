package com.demo.mapper;

import com.demo.model.StudentClass;
import com.demo.dto.AddStudentClassDTO;
import com.demo.dto.GetStudentClassDTO;
import com.demo.dto.ListStudentClassDTO;
import com.demo.dto.UpdateStudentClassDTO;

import java.util.List;

public interface StudentClassMapper {

    int save(AddStudentClassDTO addStudentClassDTO);

    int update(UpdateStudentClassDTO updateStudentClassDTO);

    StudentClass get(GetStudentClassDTO getStudentClassDTO);

    List<ListStudentClassDTO> list(ListStudentClassDTO studentClassWithScoreDTO);

}