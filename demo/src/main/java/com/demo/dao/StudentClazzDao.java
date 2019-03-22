package com.demo.dao;

import com.demo.dto.StudentClazzQuery;
import com.demo.model.StudentClazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentClazzDao {

    int save(StudentClazz studentClazz);

    int update(StudentClazz studentClazz);

    StudentClazz read(@Param("studentNum") Integer studentNum, @Param("clazzNum") Integer clazzNum);

    List<StudentClazz> list(StudentClazzQuery studentClazzQuery);

    int delete(@Param("studentNum") Integer studentNum, @Param("clazzNum") Integer clazzNum);

}