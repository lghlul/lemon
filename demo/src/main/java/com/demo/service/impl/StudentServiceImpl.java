package com.demo.service.impl;

import com.demo.model.Student;
import com.demo.model.StudentClass;
import com.demo.dto.*;
import com.demo.dao.StudentClassDao;
import com.demo.dao.StudentDao;
import com.demo.dao.TeacherClassDao;
import com.demo.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentClassDao studentClassDao;


    @Override
    public PageInfo<Student> list(ListStudentDTO listStudentDTO) throws Exception {
        PageHelper.startPage(listStudentDTO.getOffset(), listStudentDTO.getLimit());
        List<Student> studentList = studentDao.list(listStudentDTO);
        //得到分页的结果对象
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        return pageInfo;
    }


    @Override
    public List<ListStudentClassDTO> listStudentClass(ListStudentClassDTO listStudentClassDTO) throws Exception {
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        List<ListStudentClassDTO> studentClassList = this.studentClassDao.list(listStudentClassDTO);
        return studentClassList;
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
    public boolean checkStudentClassExist(StudentClassDTO studentClassDTO) {
        StudentClass studentClass = this.studentClassDao.get(studentClassDTO);
        if (studentClass == null) {
            //没有选课
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

    @Override
    public StudentClassDTO saveOrUpdateStudentClass(StudentClassDTO studentClassDTO) throws Exception {
        if (studentClassDTO.getStudentClassId() != null) {
            this.studentClassDao.update(studentClassDTO);
        } else {
            studentClassDTO.setCreateTime(System.currentTimeMillis());
            this.studentClassDao.save(studentClassDTO);
        }
        return studentClassDTO;
    }
}
