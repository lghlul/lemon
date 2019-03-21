package com.demo.dao;

import com.demo.dto.StudentClazzDTO;
import com.demo.dto.StudentClazzQueryDTO;
import com.demo.model.StudentClazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentClazzDao {

    int save(StudentClazzDTO studentClazzDTO);

    int update(StudentClazzDTO studentClazzDTO);

    StudentClazz get(@Param("studentNumber") int studentNumber, @Param("clazzNumber") int clazzNumber);

    List<StudentClazzDTO> list(StudentClazzQueryDTO studentClazzQueryDTO);

    int delete(@Param("studentNumber") int studentNumber, @Param("clazzNumber") int clazzNumber);

}