package com.demo.service.impl;

import com.demo.model.ClassInfo;
import com.demo.dto.*;
import com.demo.dao.ClassInfoDao;
import com.demo.service.ClassInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoDao classInfoDao;

    @Override
    public ClassInfoDTO saveOrUpdate(ClassInfoDTO classInfoDTO) throws Exception {
        if (classInfoDTO.getClassId() != null) {
            classInfoDao.update(classInfoDTO);
        } else {
            classInfoDTO.setCreateTime(System.currentTimeMillis());
            classInfoDao.save(classInfoDTO);
        }
        return classInfoDTO;
    }


    @Override
    public void delete(Integer classId) throws Exception {
        classInfoDao.delete(classId);
    }


    @Override
    public ClassInfo get(ClassInfoDTO classInfoDTO) {
        return classInfoDao.get(classInfoDTO);
    }

    @Override
    public boolean checkClassExist(ClassInfoDTO classInfoDTO) throws Exception {
        ClassInfo classInfo = classInfoDao.get(classInfoDTO);
        if (classInfo == null) {
            return false;
        }
        return true;
    }


    @Override
    public PageInfo<ClassInfo> list(ListClassInfoDTO listClassInfoDTO) throws Exception {
        PageHelper.startPage(listClassInfoDTO.getOffset(), listClassInfoDTO.getLimit());
        List<ClassInfo> classInfoList = classInfoDao.list(listClassInfoDTO);
        //得到分页的结果对象
        PageInfo<ClassInfo> pageInfo = new PageInfo<>(classInfoList);
        return pageInfo;
    }
}
