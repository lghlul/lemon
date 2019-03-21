package com.demo.service.impl;

import com.demo.model.Student;
import com.demo.dto.*;
import com.demo.dao.StudentDao;
import com.demo.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public Object list(StudentQueryDTO studentQueryDTO) throws Exception {
        if (studentQueryDTO.getPaging()) {
            PageHelper.startPage(studentQueryDTO.getOffset(), studentQueryDTO.getLimit());
            if(studentQueryDTO.getSort() != null){
                PageHelper.orderBy(studentQueryDTO.getSort() + " " + studentQueryDTO.getSortDir());
            }
            List<Student> studentList = studentDao.list(studentQueryDTO);
            //得到分页的结果对象
            PageInfo<Student> pageInfo = new PageInfo<>(studentList);
            return pageInfo;
        } else {
            List<Student> studentList = studentDao.list(studentQueryDTO);
            return studentList;
        }


    }


    @Override
    public void delete(Integer studentId) throws Exception {
        studentDao.delete(studentId);
    }

    @Override
    public Student read(Integer studentNumber) {
        return this.studentDao.get(studentNumber);
    }


    @Override
    public boolean checkStudentNumberExist(Integer studentNumber) {
        Student student = studentDao.get(studentNumber);
        if (student == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public StudentDTO saveOrUpdate(StudentDTO studentDTO) throws Exception {
        if (studentDTO.getStudentNumber() != null) {
            this.studentDao.update(studentDTO);
        } else {
            this.studentDao.save(studentDTO);
        }
        return studentDTO;
    }


    @Override
    public Student readById(String studentId) {
        Student student = this.studentDao.getById(studentId);
        return student;
    }
}
