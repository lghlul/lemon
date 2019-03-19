package com.demo.mapper;

import com.demo.model.TeacherClass;
import com.demo.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassMapper {

    int save(AddTeacherClassDTO addTeacherClassDTO);

    TeacherClass get(GetTeacherClassDTO getTeacherClassDTO);

    List<ListTeacherClassDTO> list(ListTeacherClassDTO listTeacherClassDTO);

    List<ListTeacherClassByTermDTO> listClass(@Param("teacherNumber") String teacherNumber);

    List<ListTeacherClassScoreDTO> listTeacherByTeacherLevel();

}