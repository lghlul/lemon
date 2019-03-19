package com.demo.mapper;

import com.demo.dto.UpdateClassDTO;
import com.demo.model.ClassInfo;
import com.demo.dto.AddClassDTO;


public interface ClassInfoMapper {
    int save(AddClassDTO addClassDTO);

    ClassInfo get(String classNumber);

    int delete(String classNumber);

    int update(UpdateClassDTO updateClassDTO);

}