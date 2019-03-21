package com.demo.service.impl;

import com.demo.dao.ClazzDao;
import com.demo.dao.TeacherClazzDao;
import com.demo.dao.TeacherDao;
import com.demo.dto.*;
import com.demo.model.Clazz;
import com.demo.model.Teacher;
import com.demo.model.TeacherClazz;
import com.demo.service.TeacherClazzService;
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
public class TeacherClazzServiceImpl implements TeacherClazzService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherClazzDao teacherClazzDao;

    @Autowired
    private ClazzDao clazzDao;


    @Override
    public TeacherClazzDTO saveOrUpdate(TeacherClazzDTO teacherClazzDTO) throws Exception {
        /*if (teacherClazzDTO.getClazzNumber() != null && teacherClazzDTO.getTeacherNumber() != null && teacherClazzDTO.getTerm() != null) {
            teacherClazzDao.update(teacherClazzDTO);
        } else {
            teacherClazzDao.save(teacherClazzDTO);
        }*/
        //目前没有用于更新的字段  只考虑添加
        teacherClazzDao.save(teacherClazzDTO);
        return teacherClazzDTO;
    }


    @Override
    public Object list(TeacherClazzQueryDTO teacherClazzQueryDTO) throws Exception {
        if(teacherClazzQueryDTO.getPaging()){
            PageHelper.startPage(teacherClazzQueryDTO.getOffset(), teacherClazzQueryDTO.getLimit());
            if(teacherClazzQueryDTO.getSort() != null){
                PageHelper.orderBy(teacherClazzQueryDTO.getSort() + " " + teacherClazzQueryDTO.getSortDir());
            }
            //关联属性不应该关联表查询   应将关联表查询 存入缓存
            List<TeacherClazzDTO> teacherClazzDTOList = teacherClazzDao.list(teacherClazzQueryDTO);
            //得到分页的结果对象
            PageInfo<TeacherClazzDTO> pageInfo = new PageInfo<>(teacherClazzDTOList);
            List<TeacherClazzDTO> teacherClazzList = pageInfo.getList();
            listClazzAndTeacherName(teacherClazzList);
            return pageInfo;
        }else{
            List<TeacherClazzDTO> teacherClazzDTOList = teacherClazzDao.list(teacherClazzQueryDTO);
            listClazzAndTeacherName(teacherClazzDTOList);
            return teacherClazzDTOList;
        }
    }

    private void listClazzAndTeacherName(List<TeacherClazzDTO> teacherClazzDTOList){
        if (teacherClazzDTOList != null) {
            List<Clazz> clazzList = clazzDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }

            List<Teacher> teacherList = teacherDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (clazzList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherNumber(), teacher);
                }
            }
            for (TeacherClazzDTO teacherClazzDTO : teacherClazzDTOList) {
                //设置课程名称与课程编号
                Clazz clazz = clazzMap.get(teacherClazzDTO.getClazzNumber());
                if (clazz != null) {
                    teacherClazzDTO.setClazzName(clazz.getClazzName());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClazzDTO.getTeacherNumber());
                if (teacher != null) {
                    teacherClazzDTO.setTeacherName(teacher.getTeacherName());
                }
            }
        }
    }

    @Override
    public boolean checkTeacherClazzExist(Integer teacherNumber , Integer clazzNumber) {
        //检验教师课程是否存在
        TeacherClazz teacherClazz = this.teacherClazzDao.get(teacherNumber , clazzNumber);
        if (teacherClazz == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void delete(Integer teacherNumber , Integer clazzNumber) throws Exception {
        this.teacherClazzDao.delete(teacherNumber , clazzNumber);
    }


    @Override
    public List<TeacherClazzTermQueryDTO> listByTerm(Integer teacherNumber) throws Exception {
        List<TeacherClazzTermQueryDTO> listTeacherClazzByTermDTOS = this.teacherClazzDao.listClazz(teacherNumber);
        this.listClazzName(listTeacherClazzByTermDTOS);
        return listTeacherClazzByTermDTOS;
    }


    @Override
    public List<TeacherClazzTermQueryDTO> listClazzByTeacherLevel() throws Exception {
        List<TeacherClazzTermQueryDTO> listTeacherClazzByTermDTOS = this.teacherClazzDao.listClazz(null);
        this.listClazzName(listTeacherClazzByTermDTOS);
        return listTeacherClazzByTermDTOS;
    }

    /*
     * @author ll
     * @Description 给教师课程查询课程名称
     * @param List<TeacherClazzTermQueryDTO>
     * @return void
     */
    private void listClazzName(List<TeacherClazzTermQueryDTO> listTeacherClazzByTermDTOS) {
        if (listTeacherClazzByTermDTOS != null) {
            List<Clazz> clazzList = clazzDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }
            //设置课程名称与课程编号
            for (TeacherClazzTermQueryDTO teacherClazzTermQueryDTO : listTeacherClazzByTermDTOS) {
                Clazz clazz = clazzMap.get(teacherClazzTermQueryDTO.getClazzNumber());
                if (clazz != null) {
                    teacherClazzTermQueryDTO.setClazzName(clazz.getClazzName());
                    teacherClazzTermQueryDTO.setClazzId(clazz.getClazzId());
                }
            }
        }
    }

    @Override
    public List<TeacherClazzScoreQueryDTO> listTeacherByTeacherLevel() throws Exception {
        List<TeacherClazzScoreQueryDTO> listTeacherClazzScoreDTOS = this.teacherClazzDao.listTeacherByTeacherLevel();
        if (listTeacherClazzScoreDTOS != null) {
            List<Clazz> clazzList = clazzDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }

            List<Teacher> teacherList = teacherDao.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (clazzList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherNumber(), teacher);
                }
            }
            for (TeacherClazzScoreQueryDTO teacherClazzScoreQueryDTO : listTeacherClazzScoreDTOS) {
                //设置课程名称与课程编号
                Clazz clazz = clazzMap.get(teacherClazzScoreQueryDTO.getClazzNumber());
                if (clazz != null) {
                    teacherClazzScoreQueryDTO.setClazzName(clazz.getClazzName());
                    teacherClazzScoreQueryDTO.setClazzId(clazz.getClazzId());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClazzScoreQueryDTO.getTeacherId());
                if (teacher != null) {
                    teacherClazzScoreQueryDTO.setTeacherName(teacher.getTeacherName());
                    teacherClazzScoreQueryDTO.setTeacherId(teacher.getTeacherId());
                }
            }
        }
        return listTeacherClazzScoreDTOS;
    }

}
