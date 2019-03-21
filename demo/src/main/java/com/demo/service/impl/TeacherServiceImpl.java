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
    public Object list(TeacherQueryDTO listTeacherDTO) throws Exception {
        if(listTeacherDTO.getPaging()){
            PageHelper.startPage(listTeacherDTO.getOffset(), listTeacherDTO.getLimit());
            if(listTeacherDTO.getSort() != null){
                PageHelper.orderBy(listTeacherDTO.getSort() + " " + listTeacherDTO.getSortDir());
            }
            List<Teacher> teacherList = teacherDao.list(listTeacherDTO);
            //得到分页的结果对象
            PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
            return pageInfo;
        }else{
            List<Teacher> teacherList = teacherDao.list(listTeacherDTO);
            return teacherList;
        }

    }



    @Override
    public void delete(Integer teacherId) throws Exception {
        teacherDao.delete(teacherId);
    }

    @Override
    public TeacherDTO saveOrUpdate(TeacherDTO teacherDTO) throws Exception {
        if (teacherDTO.getTeacherNumber() != null) {
            teacherDao.update(teacherDTO);
        } else {
            teacherDao.save(teacherDTO);
        }
        return teacherDTO;
    }

    @Override
    public Teacher read(Integer teacherNumber) {
        return teacherDao.get(teacherNumber);
    }

    @Override
    public boolean checkTeacherNumberExist(Integer teacherNumber) {
        //校验 老师是否存在
        Teacher teacher = teacherDao.get(teacherNumber);
        if (teacher == null) {
            return false;
        }
        return true;
    }

    @Override
    public Teacher readById(String teacherId) {
        Teacher teacher = teacherDao.getById(teacherId);
        return teacher;
    }
}
