package com.demo.service.impl;

import com.demo.dto.*;
import com.demo.dao.*;
import com.demo.model.Teacher;
import com.demo.model.TeacherClass;
import com.demo.service.ITeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherClassDao teacherClassDao;


    @Override
    public PageInfo<Teacher> list(ListTeacherDTO listTeacherDTO) throws Exception {
        PageHelper.startPage(listTeacherDTO.getOffset(), listTeacherDTO.getLimit());
        List<Teacher> teacherList = teacherDao.list(listTeacherDTO);
        //得到分页的结果对象
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        return pageInfo;
    }

    @Override
    public TeacherClassDTO saveOrUpdateTeacherClass(TeacherClassDTO teacherClassDTO) throws Exception {
        if(teacherClassDTO.getTeacherClassId() != null){
            teacherClassDao.update(teacherClassDTO);
        }else{
            teacherClassDTO.setCreateTime(System.currentTimeMillis());
            teacherClassDao.save(teacherClassDTO);
        }

        return teacherClassDTO;
    }


    @Override
    public PageInfo<ListTeacherClassDTO> listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) throws Exception {
        PageHelper.startPage(listTeacherClassDTO.getOffset(), listTeacherClassDTO.getLimit());
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        List<ListTeacherClassDTO> teacherClassDTOList = teacherClassDao.list(listTeacherClassDTO);
        //得到分页的结果对象
        PageInfo<ListTeacherClassDTO> pagrInfo = new PageInfo<>(teacherClassDTOList);
        return pagrInfo;
    }


    @Override
    public List<ListTeacherClassByTermDTO> listByTerm(Integer teacherId) throws Exception {
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassDao.listClass(teacherId);
        return listTeacherClassByTermDTOS;
    }


    @Override
    public List<ListTeacherClassByTermDTO> listClassByTeacherLevel() throws Exception {
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassDao.listClass(null);
        return listTeacherClassByTermDTOS;
    }

    @Override
    public List<ListTeacherClassScoreDTO> listTeacherByTeacherLevel() throws Exception {
        List<ListTeacherClassScoreDTO> listTeacherClassScoreDTOS = this.teacherClassDao.listTeacherByTeacherLevel();
        return listTeacherClassScoreDTOS;
    }

    @Override
    public void delete(Integer teacherId) throws Exception {
        teacherDao.delete(teacherId);
    }

    @Override
    public TeacherDTO saveOrUpdate(TeacherDTO teacherDTO) throws Exception {
        if(teacherDTO.getTeacherId() != null){
            teacherDao.update(teacherDTO);
        }else{
            teacherDTO.setCreateTime(System.currentTimeMillis());
            teacherDao.save(teacherDTO);
        }
        return teacherDTO;
    }

    @Override
    public Teacher get(TeacherDTO teacherDTO) {
        return teacherDao.get(teacherDTO);
    }

    @Override
    public boolean checkTeacherExist(TeacherDTO teacherDTO) {
        //校验 老师是否存在
        Teacher teacher = teacherDao.get(teacherDTO);
        if (teacher == null) {
            return false;
        }
        return true;
    }


    @Override
    public boolean checkTeacherClassExist(int teacherClassId) {
        //检验这门课程是否存在
        GetTeacherClassDTO readTeacherClassDTO = new GetTeacherClassDTO();
        readTeacherClassDTO.setTeacherClassId(teacherClassId);
        TeacherClass teacherClass = this.teacherClassDao.get(readTeacherClassDTO);
        if (teacherClass == null) {
            return false;
        } else {
            return true;
        }
    }
}
