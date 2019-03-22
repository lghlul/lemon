package com.demo.service;

import com.demo.dto.*;
import com.demo.model.ClazzTermReport;
import com.demo.model.TeacherClazz;
import com.demo.model.TeacherClazzReport;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TeacherClazzService {

    /*
     * @author ll
     * @Description 添加或者更新教师课程
     * @param TeacherClazz
     * @return TeacherClazz
     */
    TeacherClazz saveOrUpdate(TeacherClazz teacherClazz) throws Exception;

    /*
     * @author ll
     * @Description 查询教师课程列表
     * @param TeacherClazzQuery
     * @return List<TeacherClazz>
     */
    List<TeacherClazz> list(TeacherClazzQuery teacherClazzQuery) throws Exception;

    /*
     * @author ll
     * @Description 分页查询教师课程列表
     * @param TeacherClazzQuery
     * @return PageInfo<TeacherClazz>
     */
    PageInfo<TeacherClazz> listPage(TeacherClazzQuery teacherClazzQuery) throws Exception;

    /*
     * @author ll
     * @Description 删除教师课程
     * @param Integer
     * @param clazzNum
     * @return void
     */
    void delete(Integer teacherNum, Integer clazzNum) throws Exception;

    /*
     * @author ll
     * @Description 按学年查询单个教师的课程列表
     * @param Integer
     * @return List<ClazzTermReport>
     */
    List<ClazzTermReport> listByTerm(Integer teacherNum) throws Exception;

    /*
     * @author ll
     * @Description 按学年查询所有教师的课程列表
     * @return List<ClazzTermReport>
     */
    List<ClazzTermReport> listClazzByTeacherLevel() throws Exception;

    /*
     * @author ll
     * @Description 查询出所有教师的课程列表
     * @return List<TeacherClazzReport>
     */
    List<TeacherClazzReport> listTeacherByTeacherLevel() throws Exception;

}
