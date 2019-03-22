package com.demo.service.impl;

import com.demo.dao.ClazzDao;
import com.demo.dao.StudentClazzDao;
import com.demo.dto.StudentClazzQuery;
import com.demo.model.StudentClazz;
import com.demo.service.StudentClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StudentClazzServiceImpl implements StudentClazzService {


    @Autowired
    private StudentClazzDao studentClazzDao;

    @Override
    public List<StudentClazz> list(StudentClazzQuery studentClazzQuery) throws Exception {
        List<StudentClazz> studentClazzList = this.studentClazzDao.list(studentClazzQuery);
        return studentClazzList;
    }


    @Override
    public boolean checkStudentClazzExist(Integer studentNumber, Integer clazzNumber) {
        StudentClazz studentClazz = this.studentClazzDao.read(studentNumber, clazzNumber);
        if (studentClazz == null) {
            //没有选课
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StudentClazz saveOrUpdate(StudentClazz studentClazz) throws Exception {
        if (studentClazz.getClazzNumber() != null && studentClazz.getStudentNumber() != null && studentClazz.getScore() != null) {
            this.studentClazzDao.update(studentClazz);
        } else {
            this.studentClazzDao.save(studentClazz);
        }
        return studentClazz;
    }

    @Override
    public void delete(Integer studentNumber, Integer clazzNumber) throws Exception {
        this.studentClazzDao.delete(studentNumber, clazzNumber);
    }
}
