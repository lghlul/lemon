package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface TeacherService {

    /*
     * @author ll
     * @Description 查询教师列表
     * @param TeacherQuery
     * @return List<TeacherConveter>
     */
    List<Teacher> list(TeacherQuery teacherQuery) throws Exception;

    /*
     * @author ll
     * @Description 分页查询教师列表
     * @param TeacherQuery
     * @return PageInfo<TeacherConveter>
     */
    PageInfo<Teacher> listPage(TeacherQuery teacherQuery) throws Exception;

    /*
     * @author ll
     * @Description 删除教师信息
     * @param Integer
     * @return void
     */
    void delete(Integer teacherNum) throws Exception;

    /*
     * @author ll
     * @Description 添加或者更新教师
     * @param TeacherConveter
     * @return TeacherConveter
     */
    Teacher saveOrUpdate(Teacher teacher) throws Exception;

    /*
     * @author ll
     * @Description 根据主键查询教师信息
     * @param Integer
     * @return TeacherConveter
     */
    Teacher read(Integer teacherNum);

    /*
     * @author ll
     * @Description 根据教师号查询教师信息
     * @param String
     * @return TeacherConveter
     */
    Teacher readById(String teacherId);

}
