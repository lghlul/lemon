package com.demo.service.impl;

import com.demo.model.Clazz;
import com.demo.dto.*;
import com.demo.dao.ClazzDao;
import com.demo.service.ClazzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public ClazzDTO saveOrUpdate(ClazzDTO clazzDTO) throws Exception {
        if (clazzDTO.getClazzNumber() != null) {
            clazzDao.update(clazzDTO);
        } else {
            clazzDao.save(clazzDTO);
        }
        return clazzDTO;
    }


    @Override
    public void delete(Integer clazzNumber) throws Exception {
        clazzDao.delete(clazzNumber);
    }


    @Override
    public Clazz read(Integer clazzNumber) {
        return clazzDao.get(clazzNumber);
    }

    @Override
    public Clazz readById(String clazzId) throws Exception {
        Clazz clazz = clazzDao.getById(clazzId);
        return clazz;
    }

    @Override
    public boolean checkClazzNumberExist(Integer clazzNumber) throws Exception {
        Clazz clazz = clazzDao.get(clazzNumber);
        if(clazz == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Object list(ClazzQueryDTO clazzQueryDTO) throws Exception {
        if(clazzQueryDTO.getPaging()){
            PageHelper.startPage(clazzQueryDTO.getOffset(), clazzQueryDTO.getLimit());
            if(clazzQueryDTO.getSort() != null){
                PageHelper.orderBy(clazzQueryDTO.getSort() + " " + clazzQueryDTO.getSortDir());
            }
            List<Clazz> clazzList = clazzDao.list(clazzQueryDTO);
            //得到分页的结果对象
            PageInfo<Clazz> pageInfo = new PageInfo<>(clazzList);
            return pageInfo;
        }else{
            List<Clazz> clazzList = clazzDao.list(clazzQueryDTO);
            return clazzList;
        }

    }
}
