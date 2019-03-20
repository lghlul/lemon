package com.demo.service;

import com.demo.dto.*;
import com.demo.model.ClassInfo;
import com.github.pagehelper.PageInfo;

public interface IClassInfoService {


    void delete(Integer classId) throws Exception;

    PageInfo<ClassInfo> list(ListClassInfoDTO listClassInfoDTO) throws Exception;

    ClassInfo get(ClassInfoDTO classInfoDTO) throws Exception;

    boolean checkClassExist(ClassInfoDTO classInfoDTO) throws Exception;

    ClassInfoDTO saveOrUpdate(ClassInfoDTO classInfoDTO) throws Exception;

}
