package com.demo.service.impl;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.mapper.*;
import com.demo.model.Student;
import com.demo.model.Teacher;
import com.demo.model.TeacherClass;
import com.demo.service.ITeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;


    @Override
    public ResultBean save(AddTeacherDTO addTeacherDTO) throws Exception {
        addTeacherDTO.setCreateTime(System.currentTimeMillis());
        if (teacherMapper.save(addTeacherDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS, addTeacherDTO);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public ResultBean list(ListTeacherDTO listTeacherDTO) throws Exception {
        PageHelper.startPage(listTeacherDTO.getOffset() , listTeacherDTO.getLimit());
        List<Teacher> teacherList = teacherMapper.list(listTeacherDTO);
        //得到分页的结果对象
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        return new ResultBean(ResultCodeConstant.SUCCESS, pageInfo);
    }

    @Override
    public ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) throws Exception {
        addTeacherClassDTO.setCreateTime(System.currentTimeMillis());
        if (this.teacherClassMapper.save(addTeacherClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS , addTeacherClassDTO);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) throws Exception {
        PageHelper.startPage(listTeacherClassDTO.getOffset() , listTeacherClassDTO.getLimit());
        List<ListTeacherClassDTO> teacherClassDTOList = teacherClassMapper.list(listTeacherClassDTO);
        //得到分页的结果对象
        PageInfo<ListTeacherClassDTO> pagrInfo = new PageInfo<>(teacherClassDTOList);
        return new ResultBean(ResultCodeConstant.SUCCESS, pagrInfo);
    }


    @Override
    public ResultBean listByTerm(String teacherNumber) throws Exception {
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassMapper.listClass(teacherNumber);
        return new ResultBean(ResultCodeConstant.SUCCESS, listTeacherClassByTermDTOS);
    }


    @Override
    public ResultBean listClassByTeacherLevel(String teacherNumber) throws Exception {
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassMapper.listClass(null);
        return new ResultBean(ResultCodeConstant.SUCCESS, listTeacherClassByTermDTOS);
    }

    @Override
    public ResultBean listTeacherByTeacherLevel(String teacherNumber) throws Exception {
        List<ListTeacherClassScoreDTO> listTeacherClassScoreDTOS = this.teacherClassMapper.listTeacherByTeacherLevel();
        return new ResultBean(ResultCodeConstant.SUCCESS, listTeacherClassScoreDTOS);
    }

    @Override
    public ResultBean delete(String teacherNumber) throws Exception {
        if (teacherMapper.delete(teacherNumber) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public ResultBean update(UpdateTeacherDTO updateTeacherDTO) throws Exception {
        if (teacherMapper.update(updateTeacherDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public Teacher get(String teacherNumber) {
        return teacherMapper.get(teacherNumber);
    }

    @Override
    public boolean checkTeacherExist(String teacherNumber) {
        //校验 老师是否存在
        Teacher teacher = teacherMapper.get(teacherNumber);
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
        TeacherClass teacherClass = this.teacherClassMapper.get(readTeacherClassDTO);
        if (teacherClass == null) {
            return false;
        } else {
            return true;
        }
    }
}
