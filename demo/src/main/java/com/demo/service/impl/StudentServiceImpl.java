package com.demo.service.impl;

import com.demo.constant.ResultCodeConstant;
import com.demo.model.Student;
import com.demo.model.StudentClass;
import com.demo.model.TeacherClass;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.mapper.StudentClassMapper;
import com.demo.mapper.StudentMapper;
import com.demo.mapper.TeacherClassMapper;
import com.demo.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;

    @Override
    public ResultBean save(AddStudentDTO addStudentDTO) throws Exception {
        addStudentDTO.setCreateTime(System.currentTimeMillis());
        if (studentMapper.save(addStudentDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS , addStudentDTO);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean list(ListStudentDTO listStudentDTO) throws Exception {
        PageHelper.startPage(listStudentDTO.getOffset() , listStudentDTO.getLimit());
        List<Student> studentList = studentMapper.list(listStudentDTO);
        //得到分页的结果对象
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        return new ResultBean(ResultCodeConstant.SUCCESS, pageInfo);
    }


    @Override
    public ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO) throws Exception {

        addStudentClassDTO.setCreateTime(System.currentTimeMillis());
        if (this.studentClassMapper.save(addStudentClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS , addStudentClassDTO);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO) throws Exception {
        //录入分数
        if (this.studentClassMapper.update(updateStudentClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean listStudentClass(ListStudentClassDTO listStudentClassDTO) throws Exception {
        List<ListStudentClassDTO> studentClassList = this.studentClassMapper.list(listStudentClassDTO);
        return new ResultBean(ResultCodeConstant.SUCCESS, studentClassList);
    }


    @Override
    public ResultBean delete(String studentNumber) throws Exception {
        if (studentMapper.delete(studentNumber) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public ResultBean update(UpdateStudentDTO updateClassDTO) throws Exception {
        if (studentMapper.update(updateClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public Student get(String studentNumber) {
        return this.studentMapper.get(studentNumber);
    }


    @Override
    public boolean checkStudentExist(String studentNumber) {
        Student student = studentMapper.get(studentNumber);
        if (student == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkStudentClassExist(String studentNumber, int teacherClassId) {
        GetStudentClassDTO getStudentClassDTO = new GetStudentClassDTO();
        getStudentClassDTO.setStudentNumber(studentNumber);
        getStudentClassDTO.setTeacherClassId(teacherClassId);
        StudentClass studentClass = this.studentClassMapper.get(getStudentClassDTO);
        if (studentClass == null) {
            //没有选课
            return false;
        } else {
            return true;
        }
    }

}
