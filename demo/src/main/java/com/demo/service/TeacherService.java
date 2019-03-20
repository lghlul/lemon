package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TeacherService {

    PageInfo<Teacher> list(ListTeacherDTO listTeacherDTO) throws Exception;

    List<ListTeacherClassByTermDTO> listByTerm(Integer teacherId) throws Exception;

    List<ListTeacherClassByTermDTO> listClassByTeacherLevel() throws Exception;

    List<ListTeacherClassScoreDTO> listTeacherByTeacherLevel() throws Exception;

    void delete(Integer teacherId) throws Exception;

    TeacherDTO saveOrUpdate(TeacherDTO teacherDTO) throws Exception;

    Teacher get(TeacherDTO teacherDTO);

    boolean checkTeacherExist(TeacherDTO teacherDTO);


}
