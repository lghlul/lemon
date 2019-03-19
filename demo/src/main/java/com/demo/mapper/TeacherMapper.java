package com.demo.mapper;

import com.demo.domain.DO.TeacherDO;
import com.demo.domain.DTO.AddTeacherDTO;
import com.demo.domain.DTO.ListTeacherDTO;

import java.util.List;

public interface TeacherMapper {
    int saveTeacher(AddTeacherDTO addTeacherDTO);

    TeacherDO getTeacher(String teacherNumber);

    List<TeacherDO> listTeacher(ListTeacherDTO teacherDTO);

    int countTeacher(ListTeacherDTO teacherDTO);


}