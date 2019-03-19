package com.demo.mapper;

import com.demo.domain.DO.TeacherClassDO;
import com.demo.domain.DTO.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassMapper {

    int saveTeacherClass(AddTeacherClassDTO addTeacherClassDtO);

    TeacherClassDO getTeacherClass(GetTeacherClassDTO getTeacherClassDTO);

    List<ListTeacherClassDTO> listTeacherClass(ListTeacherClassDTO listTeacherClassDTO);

    int countTeacherClass(ListTeacherClassDTO listTeacherClassDTO);

    List<ListTeacherClassByTermDTO> listTeacherClassByTerm(@Param("teacherNumber") String teacherNumber);

    List<ListTeacherClassScoreDTO> listTeacherClassScore();

}