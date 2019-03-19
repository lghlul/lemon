package com.demo.service;

import com.demo.dto.*;
import com.demo.common.ResultBean;

public interface IClassInfoService {

    ResultBean save(AddClassDTO addClassDTO) throws Exception;

    ResultBean delete(String classNumber) throws Exception;

    ResultBean update(UpdateClassDTO updateClassDTO) throws Exception;

}
