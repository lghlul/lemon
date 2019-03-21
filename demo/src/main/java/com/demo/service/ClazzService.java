package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Clazz;

public interface ClazzService {


    void delete(Integer clazzNumber) throws Exception;

    Object list(ClazzQueryDTO clazzQueryDTO) throws Exception;

    Clazz read(Integer clazzNumber) throws Exception;

    Clazz readById(String clazzId) throws Exception;

    boolean checkClazzNumberExist(Integer clazzNumber) throws Exception;

    ClazzDTO saveOrUpdate(ClazzDTO clazzDTO) throws Exception;

}
