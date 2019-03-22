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
    public List<Student> list(StudentQuery studentQuery) throws Exception {
        List<Student> studentList = studentDao.list(studentQuery);
        return studentList;
    }

    @Override
    public PageInfo<Student> listPage(StudentQuery studentQuery) throws Exception {
        PageHelper.startPage(studentQuery.getOffset(), studentQuery.getLimit());
        if (studentQuery.getSort() != null) {
            PageHelper.orderBy(studentQuery.getSort() + " " + studentQuery.getSortDir());
        }
        List<Student> studentList = studentDao.list(studentQuery);
        //得到分页的结果对象
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        return pageInfo;
    }


    @Override
    public void delete(Integer studentId) throws Exception {
        studentDao.delete(studentId);
    }

    @Override
    public Student read(Integer studentNumber) {
        return this.studentDao.read(studentNumber);
    }


    @Override
    public Student saveOrUpdate(Student student) throws Exception {
        if (student.getStudentNumber() != null) {
            this.studentDao.update(student);
        } else {
            this.studentDao.save(student);
        }
        return student;
    }


    @Override
    public Student readById(String studentId) {
        Student student = this.studentDao.readById(studentId);
        return student;
    }
}
