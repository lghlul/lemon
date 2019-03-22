package com.demo.dao;

import com.demo.dto.ClazzQuery;
import com.demo.model.Clazz;

import java.util.List;


public interface ClazzDao {
    int save(Clazz Clazz);

    Clazz read(Integer clazzNum);

    Clazz readById(String clazzId);

    int delete(Integer clazzNum);

    int update(Clazz clazz);

    List<Clazz> list(ClazzQuery clazzQuery);

}