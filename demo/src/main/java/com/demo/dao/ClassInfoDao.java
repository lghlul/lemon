package com.demo.dao;

import com.demo.dto.ListClassInfoDTO;
import com.demo.model.ClassInfo;
import com.demo.dto.ClassInfoDTO;

import java.util.List;


public interface ClassInfoDao {
    int save(ClassInfoDTO classInfoDTO);

    ClassInfo get(ClassInfoDTO classInfoDTO);

    int delete(Integer classId);

    int update(ClassInfoDTO classInfoDTO);

    List<ClassInfo> list(ListClassInfoDTO listClassInfoDTO);

}