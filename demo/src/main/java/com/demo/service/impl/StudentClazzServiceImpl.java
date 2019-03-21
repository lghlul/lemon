package com.demo.service.impl;

import com.demo.dao.ClazzDao;
import com.demo.dao.StudentClazzDao;
import com.demo.dto.StudentClazzDTO;
import com.demo.dto.StudentClazzQueryDTO;
import com.demo.model.Clazz;
import com.demo.model.StudentClazz;
import com.demo.service.StudentClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StudentClazzServiceImpl implements StudentClazzService {


    @Autowired
    private StudentClazzDao studentClazzDao;

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public List<StudentClazzDTO> list(StudentClazzQueryDTO studentClazzQueryDTO) throws Exception {
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        List<StudentClazzDTO> studentClazzDTOList = this.studentClazzDao.list(studentClazzQueryDTO);

        if (studentClazzDTOList != null) {
            List<Clazz> clazzList = clazzDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }
            for (StudentClazzDTO studentClazzDTO : studentClazzDTOList) {
                //设置教师名称与教师编号
                Clazz clazz = clazzMap.get(studentClazzDTO.getClazzNumber());
                if (clazz != null) {
                    studentClazzDTO.setClazzName(clazz.getClazzName());
                }
            }
        }
        return studentClazzDTOList;
    }


    @Override
    public boolean checkStudentClazzExist(Integer studentNumber, Integer clazzNumber) {
        StudentClazz studentClazz = this.studentClazzDao.get(studentNumber, clazzNumber);
        if (studentClazz == null) {
            //没有选课
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StudentClazzDTO saveOrUpdate(StudentClazzDTO studentClazzDTO) throws Exception {
        if (studentClazzDTO.getClazzNumber() != null && studentClazzDTO.getStudentNumber() != null && studentClazzDTO.getScore() != null) {
            this.studentClazzDao.update(studentClazzDTO);
        } else {
            this.studentClazzDao.save(studentClazzDTO);
        }
        return studentClazzDTO;
    }

    @Override
    public void delete(Integer studentNumber, Integer clazzNumber) throws Exception {
        this.studentClazzDao.delete(studentNumber, clazzNumber);
    }
}
