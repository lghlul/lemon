package com.demo.service.impl;

import com.demo.domain.DO.ClassDO;
import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;
import com.demo.common.ResultCodeEnum;
import com.demo.mapper.ClassMapper;
import com.demo.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassMapper classMapper;


    @Override
    public ResultBean addClass(AddClassDTO addClassDTO) {
        ClassDO classDO = classMapper.getClass(addClassDTO.getClassNumber());
        if (classDO == null) {
            //编号不存在 可以添加
            addClassDTO.setCreateTime(System.currentTimeMillis());
            if (classMapper.saveClass(addClassDTO) > 0) {
                return ResultCodeEnum.SUCCESS.getResponse();
            } else {
                return ResultCodeEnum.FAIL.getResponse();
            }
        } else {
            //编号重复
            return ResultCodeEnum.NUMBER_REPEAT.getResponse();
        }
    }

}
