package com.demo.dao;

import com.demo.dto.ClazzQuery;
import com.demo.model.Clazz;

import java.util.List;


public interface ClazzDao {
    int save(Clazz Clazz);

    Clazz read(Integer clazzNumber);

    Clazz readById(String clazzId);

    int delete(Integer clazzNumber);

    int update(Clazz clazz);

    List<Clazz> list(ClazzQuery clazzQuery);

}