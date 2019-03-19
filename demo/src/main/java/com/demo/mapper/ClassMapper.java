package com.demo.mapper;

import com.demo.domain.DO.ClassDO;
import com.demo.domain.DTO.AddClassDTO;


public interface ClassMapper {
    int saveClass(AddClassDTO addClassDTO);

    ClassDO getClass(String ClassNumber);


}