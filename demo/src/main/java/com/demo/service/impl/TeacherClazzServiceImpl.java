package com.demo.service.impl;

import com.demo.dao.ClazzDao;
import com.demo.dao.TeacherClazzDao;
import com.demo.dao.TeacherDao;
import com.demo.dto.*;
import com.demo.model.*;
import com.demo.service.TeacherClazzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TeacherClazzServiceImpl implements TeacherClazzService {

    @Autowired
    private TeacherClazzDao teacherClazzDao;


    @Override
    public TeacherClazz saveOrUpdate(TeacherClazz teacherClazz) throws Exception {
        /*if (teacherClazzDTO.getClazzNumber() != null && teacherClazzDTO.getTeacherNumber() != null && teacherClazzDTO.getTerm() != null) {
            teacherClazzDao.update(teacherClazzDTO);
        } else {
            teacherClazzDao.save(teacherClazzDTO);
        }*/
        //目前没有用于更新的字段  只考虑添加
        teacherClazzDao.save(teacherClazz);
        return teacherClazz;
    }


    @Override
    public List<TeacherClazz> list(TeacherClazzQuery teacherClazzQuery) throws Exception {
        List<TeacherClazz> teacherClazzList = teacherClazzDao.list(teacherClazzQuery);
        return teacherClazzList;
    }

    @Override
    public PageInfo<TeacherClazz> listPage(TeacherClazzQuery teacherClazzQuery) throws Exception {
        PageHelper.startPage(teacherClazzQuery.getOffset(), teacherClazzQuery.getLimit());
        if (teacherClazzQuery.getSort() != null) {
            PageHelper.orderBy(teacherClazzQuery.getSort() + " " + teacherClazzQuery.getSortDir());
        }
        List<TeacherClazz> teacherClazzDTOList = teacherClazzDao.list(teacherClazzQuery);
        //得到分页的结果对象
        PageInfo<TeacherClazz> pageInfo = new PageInfo<>(teacherClazzDTOList);
        return pageInfo;
    }


    @Override
    public void delete(Integer teacherNumber, Integer clazzNumber) throws Exception {
        this.teacherClazzDao.delete(teacherNumber, clazzNumber);
    }


    @Override
    public List<ClazzTermReport> listByTerm(Integer teacherNumber) throws Exception {
        List<ClazzTermReport> clazzTermReports = this.teacherClazzDao.listClazz(teacherNumber);
        return clazzTermReports;
    }


    @Override
    public List<ClazzTermReport> listClazzByTeacherLevel() throws Exception {
        List<ClazzTermReport> clazzTermReports = this.teacherClazzDao.listClazz(null);
        return clazzTermReports;
    }

    @Override
    public List<TeacherClazzReport> listTeacherByTeacherLevel() throws Exception {
        List<TeacherClazzReport> teacherClazzReports = this.teacherClazzDao.listTeacherByTeacherLevel();
        return teacherClazzReports;
    }

}
