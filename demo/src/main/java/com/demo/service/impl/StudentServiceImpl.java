package com.demo.service.impl;

import com.demo.dao.ClassInfoDao;
import com.demo.model.Student;
import com.demo.dto.*;
import com.demo.dao.StudentClassDao;
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
    public PageInfo<Student> list(ListStudentDTO listStudentDTO) throws Exception {
        PageHelper.startPage(listStudentDTO.getOffset(), listStudentDTO.getLimit());
        List<Student> studentList = studentDao.list(listStudentDTO);
        //得到分页的结果对象
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        return pageInfo;
    }




    @Override
    public void delete(Integer studentId) throws Exception {
        studentDao.delete(studentId);
    }

    @Override
    public Student get(StudentDTO studentDTO) {
        return this.studentDao.get(studentDTO);
    }


    @Override
    public boolean checkStudentExist(StudentDTO studentDTO) {
        Student student = studentDao.get(studentDTO);
        if (student == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public StudentDTO saveOrUpdate(StudentDTO studentDTO) throws Exception {
        if (studentDTO.getStudentId() != null) {
            this.studentDao.update(studentDTO);
        } else {
            studentDTO.setCreateTime(System.currentTimeMillis());
            this.studentDao.save(studentDTO);
        }
        return studentDTO;
    }

}
