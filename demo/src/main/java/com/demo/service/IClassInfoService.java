package com.demo.service;

import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.ClassInfo;

public interface IClassInfoService {

    ResultBean save(AddClassDTO addClassDTO) throws Exception;

    ResultBean delete(String classNumber) throws Exception;

    ResultBean update(UpdateClassDTO updateClassDTO) throws Exception;

    ClassInfo get(String classNumber) throws Exception;

    boolean checkClassExist(String classNumber) throws Exception;

}
