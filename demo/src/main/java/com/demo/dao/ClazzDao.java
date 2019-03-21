package com.demo.dao;

import com.demo.dto.ClazzQueryDTO;
import com.demo.model.Clazz;
import com.demo.dto.ClazzDTO;

import java.util.List;


public interface ClazzDao {
    int save(ClazzDTO clazzDTO);

    Clazz get(Integer clazzNumber);

    Clazz getById(String clazzId);

    int delete(Integer clazzNumber);

    int update(ClazzDTO clazzDTO);

    List<Clazz> list(ClazzQueryDTO clazzQueryDTO);

}