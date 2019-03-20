package com.demo.service.impl;

import com.demo.dto.*;
import com.demo.dao.*;
import com.demo.model.ClassInfo;
import com.demo.model.Teacher;
import com.demo.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherClassDao teacherClassDao;

    @Autowired
    private ClassInfoDao classInfoDao;


    @Override
    public PageInfo<Teacher> list(ListTeacherDTO listTeacherDTO) throws Exception {
        PageHelper.startPage(listTeacherDTO.getOffset(), listTeacherDTO.getLimit());
        List<Teacher> teacherList = teacherDao.list(listTeacherDTO);
        //得到分页的结果对象
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        return pageInfo;
    }


    @Override
    public List<ListTeacherClassByTermDTO> listByTerm(Integer teacherId) throws Exception {
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassDao.listClass(teacherId);
        this.listClassName(listTeacherClassByTermDTOS);
        return listTeacherClassByTermDTOS;
    }


    @Override
    public List<ListTeacherClassByTermDTO> listClassByTeacherLevel() throws Exception {
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassDao.listClass(null);
        this.listClassName(listTeacherClassByTermDTOS);
        return listTeacherClassByTermDTOS;
    }

    /*
     * @author ll
     * @Description 给教师课程查询课程名称
     * @param List<ListTeacherClassByTermDTO>
     * @return void
     */
    private void listClassName(List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS) {
        if (listTeacherClassByTermDTOS != null) {
            List<ClassInfo> classInfoList = classInfoDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, ClassInfo> classInfoMap = new HashMap<>();
            if (classInfoList != null) {
                for (ClassInfo classInfo : classInfoList) {
                    classInfoMap.put(classInfo.getClassId(), classInfo);
                }
            }
            //设置课程名称与课程编号
            for (ListTeacherClassByTermDTO teacherClassByTermDTO : listTeacherClassByTermDTOS) {
                ClassInfo classInfo = classInfoMap.get(teacherClassByTermDTO.getClassId());
                if (classInfo != null) {
                    teacherClassByTermDTO.setClassName(classInfo.getClassName());
                    teacherClassByTermDTO.setClassNumber(classInfo.getClassNumber());
                }
            }
        }
    }

    @Override
    public List<ListTeacherClassScoreDTO> listTeacherByTeacherLevel() throws Exception {
        List<ListTeacherClassScoreDTO> listTeacherClassScoreDTOS = this.teacherClassDao.listTeacherByTeacherLevel();
        if (listTeacherClassScoreDTOS != null) {
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
            for (ListTeacherClassScoreDTO listTeacherClassScore : listTeacherClassScoreDTOS) {
                //设置课程名称与课程编号
                ClassInfo classInfo = classInfoMap.get(listTeacherClassScore.getClassId());
                if (classInfo != null) {
                    listTeacherClassScore.setClassName(classInfo.getClassName());
                    listTeacherClassScore.setClassNumber(classInfo.getClassNumber());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(listTeacherClassScore.getTeacherId());
                if (teacher != null) {
                    listTeacherClassScore.setTeacherName(teacher.getTeacherName());
                    listTeacherClassScore.setTeacherNumber(teacher.getTeacherNumber());
                }
            }
        }
        return listTeacherClassScoreDTOS;
    }

    @Override
    public void delete(Integer teacherId) throws Exception {
        teacherDao.delete(teacherId);
    }

    @Override
    public TeacherDTO saveOrUpdate(TeacherDTO teacherDTO) throws Exception {
        if (teacherDTO.getTeacherId() != null) {
            teacherDao.update(teacherDTO);
        } else {
            teacherDTO.setCreateTime(System.currentTimeMillis());
            teacherDao.save(teacherDTO);
        }
        return teacherDTO;
    }

    @Override
    public Teacher get(TeacherDTO teacherDTO) {
        return teacherDao.get(teacherDTO);
    }

    @Override
    public boolean checkTeacherExist(TeacherDTO teacherDTO) {
        //校验 老师是否存在
        Teacher teacher = teacherDao.get(teacherDTO);
        if (teacher == null) {
            return false;
        }
        return true;
    }

}
