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
    public Clazz saveOrUpdate(Clazz clazz) throws Exception {
        if (clazz.getClazzNum() != null) {
            clazzDao.update(clazz);
        } else {
            clazzDao.save(clazz);
        }
        return clazz;
    }


    @Override
    public void delete(Integer clazzNum) throws Exception {
        clazzDao.delete(clazzNum);
    }


    @Override
    public Clazz read(Integer clazzNum) {
        return clazzDao.read(clazzNum);
    }

    @Override
    public Clazz readById(String clazzId) throws Exception {
        Clazz clazz = clazzDao.readById(clazzId);
        return clazz;
    }

    @Override
    public List<Clazz> list(ClazzQuery clazzQuery) throws Exception {
        List<Clazz> clazzList = clazzDao.list(clazzQuery);
        return clazzList;
    }

    @Override
    public PageInfo<Clazz> listPage(ClazzQuery clazzQuery) {
        PageHelper.startPage(clazzQuery.getOffset(), clazzQuery.getLimit());
        if (clazzQuery.getSort() != null) {
            PageHelper.orderBy(clazzQuery.getSort() + " " + clazzQuery.getSortDir());
        }
        List<Clazz> clazzList = clazzDao.list(clazzQuery);
        //得到分页的结果对象
        PageInfo<Clazz> pageInfo = new PageInfo<>(clazzList);
        return pageInfo;
    }
}
