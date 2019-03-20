package com.demo.service.impl;

import com.demo.dao.ClassInfoDao;
import com.demo.dao.StudentClassDao;
import com.demo.dao.StudentDao;
import com.demo.dto.ListStudentClassDTO;
import com.demo.dto.StudentClassDTO;
import com.demo.model.ClassInfo;
import com.demo.model.StudentClass;
import com.demo.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StudentClassServiceImpl implements StudentClassService {


    @Autowired
    private StudentClassDao studentClassDao;

    @Autowired
    private ClassInfoDao classInfoDao;

    @Override
    public List<ListStudentClassDTO> list(ListStudentClassDTO listStudentClassDTO) throws Exception {
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        List<ListStudentClassDTO> studentClassList = this.studentClassDao.list(listStudentClassDTO);

        if (studentClassList != null) {
            List<ClassInfo> classInfoList = classInfoDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, ClassInfo> classInfoMap = new HashMap<>();
            if (classInfoList != null) {
                for (ClassInfo classInfo : classInfoList) {
                    classInfoMap.put(classInfo.getClassId(), classInfo);
                }
            }
            for (ListStudentClassDTO listStudentClass : studentClassList) {
                //设置教师名称与教师编号
                ClassInfo classInfo = classInfoMap.get(listStudentClass.getClassId());
                if (classInfo != null) {
                    listStudentClass.setClassName(classInfo.getClassName());
                }
            }
        }
        return studentClassList;
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
    public StudentClassDTO saveOrUpdate(StudentClassDTO studentClassDTO) throws Exception {
        if (studentClassDTO.getStudentClassId() != null) {
            this.studentClassDao.update(studentClassDTO);
        } else {
            studentClassDTO.setCreateTime(System.currentTimeMillis());
            this.studentClassDao.save(studentClassDTO);
        }
        return studentClassDTO;
    }

    @Override
    public void delete(Integer studentClassId) throws Exception {
        this.studentClassDao.delete(studentClassId);
    }
}
