package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Clazz;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClazzService {


    void delete(Integer clazzNumber) throws Exception;

    List<Clazz> list(ClazzQuery clazzQuery) throws Exception;

    PageInfo<Clazz> listPage(ClazzQuery clazzQuery);

    Clazz read(Integer clazzNumber) throws Exception;

    Clazz readById(String clazzId) throws Exception;

    Clazz saveOrUpdate(Clazz clazz) throws Exception;

}
