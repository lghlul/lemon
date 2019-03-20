package com.demo.service.impl;

import com.demo.dao.ClassInfoDao;
import com.demo.dao.TeacherClassDao;
import com.demo.dao.TeacherDao;
import com.demo.dto.*;
import com.demo.model.ClassInfo;
import com.demo.model.Teacher;
import com.demo.model.TeacherClass;
import com.demo.service.TeacherClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TeacherClassServiceImpl implements TeacherClassService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherClassDao teacherClassDao;

    @Autowired
    private ClassInfoDao classInfoDao;


    @Override
    public TeacherClassDTO saveOrUpdate(TeacherClassDTO teacherClassDTO) throws Exception {
        if (teacherClassDTO.getTeacherClassId() != null) {
            teacherClassDao.update(teacherClassDTO);
        } else {
            teacherClassDTO.setCreateTime(System.currentTimeMillis());
            teacherClassDao.save(teacherClassDTO);
        }

        return teacherClassDTO;
    }


    @Override
    public PageInfo<TeacherClassDTO> list(ListTeacherClassDTO listTeacherClassDTO) throws Exception {
        PageHelper.startPage(listTeacherClassDTO.getOffset(), listTeacherClassDTO.getLimit());
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        List<TeacherClassDTO> teacherClassDTOList = teacherClassDao.list(listTeacherClassDTO);
        //得到分页的结果对象
        PageInfo<TeacherClassDTO> pageInfo = new PageInfo<>(teacherClassDTOList);
        List<TeacherClassDTO> teacherClassList = pageInfo.getList();

        if (teacherClassList != null) {
            List<ClassInfo> classInfoList = classInfoDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, ClassInfo> classInfoMap = new HashMap<>();
            if (classInfoList != null) {
                for (ClassInfo classInfo : classInfoList) {
                    classInfoMap.put(classInfo.getClassId(), classInfo);
                }
            }

            List<Teacher> teacherList = teacherDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (classInfoList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherId(), teacher);
                }
            }
            for (TeacherClassDTO teacherClassDTO : teacherClassList) {
                //设置课程名称与课程编号
                ClassInfo classInfo = classInfoMap.get(teacherClassDTO.getClassId());
                if (classInfo != null) {
                    teacherClassDTO.setClassName(classInfo.getClassName());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClassDTO.getTeacherId());
                if (teacher != null) {
                    teacherClassDTO.setTeacherName(teacher.getTeacherName());
                }
            }
        }
        return pageInfo;
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
