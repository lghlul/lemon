package com.demo.dao;

import com.demo.model.TeacherClass;
import com.demo.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassDao {

    int save(TeacherClassDTO teacherClassDTO);

    int update(TeacherClassDTO teacherClassDTO);

    TeacherClass get(GetTeacherClassDTO getTeacherClassDTO);

    List<TeacherClassDTO> list(ListTeacherClassDTO listTeacherClassDTO);

    List<ListTeacherClassByTermDTO> listClass(@Param("teacherId") Integer teacherId);

    List<ListTeacherClassScoreDTO> listTeacherByTeacherLevel();

    int delete(int teacherClassId);

}