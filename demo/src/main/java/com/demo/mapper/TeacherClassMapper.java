package com.demo.mapper;

import com.demo.model.TeacherClass;
import com.demo.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassMapper {

    int save(AddTeacherClassDTO addTeacherClassDTO);

    TeacherClass get(GetTeacherClassDTO getTeacherClassDTO);

    List<ListTeacherClassDTO> list(ListTeacherClassDTO listTeacherClassDTO);

    int count(ListTeacherClassDTO listTeacherClassDTO);

    List<ListTeacherClassByTermDTO> listByTerm(@Param("teacherNumber") String teacherNumber);

    List<ListTeacherClassScoreDTO> listWithScore();

}