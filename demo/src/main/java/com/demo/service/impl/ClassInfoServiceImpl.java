package com.demo.service.impl;

import com.demo.constant.ResultCodeConstant;
import com.demo.model.ClassInfo;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.mapper.ClassInfoMapper;
import com.demo.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassInfoServiceImpl implements IClassInfoService {

    @Autowired
    private ClassInfoMapper classMapper;

    @Override
    public ResultBean save(AddClassDTO addClassDTO)  throws Exception{
        ClassInfo classDO = classMapper.get(addClassDTO.getClassNumber());
        if (classDO == null) {
            //编号不存在 可以添加
            addClassDTO.setCreateTime(System.currentTimeMillis());
            if (classMapper.save(addClassDTO) > 0) {
                return new ResultBean(ResultCodeConstant.SUCCESS);
            } else {
                return new ResultBean(ResultCodeConstant.FAIL);
            }
        } else {
            //编号重复
            return new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
        }
    }


    @Override
    public ResultBean delete(String classNumber) throws Exception {
        if (classMapper.delete(classNumber) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public ResultBean update(UpdateClassDTO updateClassDTO) throws Exception {
        if (classMapper.update(updateClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }
}
