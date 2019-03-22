package com.demo.service.impl;

import com.demo.dto.*;
import com.demo.dao.*;
import com.demo.model.Teacher;
import com.demo.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> list(TeacherQuery teacherQuery) throws Exception {
        List<Teacher> teacherList = teacherDao.list(teacherQuery);
        return teacherList;

    }

    @Override
    public PageInfo<Teacher> listPage(TeacherQuery teacherQuery) throws Exception {
        PageHelper.startPage(teacherQuery.getOffset(), teacherQuery.getLimit());
        if (teacherQuery.getSort() != null) {
            PageHelper.orderBy(teacherQuery.getSort() + " " + teacherQuery.getSortDir());
        }
        List<Teacher> teacherList = teacherDao.list(teacherQuery);
        //得到分页的结果对象
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        return pageInfo;
    }

    @Override
    public void delete(Integer teacherId) throws Exception {
        teacherDao.delete(teacherId);
    }

    @Override
    public Teacher saveOrUpdate(Teacher teacher) throws Exception {
        if (teacher.getTeacherNum() != null) {
            teacherDao.update(teacher);
        } else {
            teacherDao.save(teacher);
        }
        return teacher;
    }

    @Override
    public Teacher read(Integer teacherNum) {
        return teacherDao.read(teacherNum);
    }

    @Override
    public Teacher readById(String teacherId) {
        Teacher teacher = teacherDao.readById(teacherId);
        return teacher;
    }
}
