package com.demo.service;

import com.demo.dto.*;
import com.demo.model.ClassInfo;
import com.github.pagehelper.PageInfo;

public interface ClassInfoService {


    void delete(Integer classId) throws Exception;

    Object list(ListClassInfoDTO listClassInfoDTO) throws Exception;

    ClassInfo get(ClassInfoDTO classInfoDTO) throws Exception;

    boolean checkClassExist(ClassInfoDTO classInfoDTO) throws Exception;

    ClassInfoDTO saveOrUpdate(ClassInfoDTO classInfoDTO) throws Exception;

}
